package dev.playsit.ui.modules.feed

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import dev.playsit.core.utils.toLiveData
import dev.playsit.model.Board
import dev.playsit.model.Compilation
import dev.playsit.model.Feed
import dev.playsit.repository.FeedRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val repository: FeedRepository) : ViewModel() {

    private val _compilations = MutableLiveData<Compilation>()
    val compilations = _compilations.toLiveData()

    private val _popularCompilation = MutableLiveData<Compilation>()
    val popularCompilation = _popularCompilation.toLiveData()

    private val _videoCompilation = MutableLiveData<Compilation>()
    val videoCompilation = _videoCompilation.toLiveData()

    private val _otherCompilations = MutableLiveData(mutableListOf<MutableLiveData<Compilation>>())
    val otherCompilations = _otherCompilations.toLiveData()

    private val _spinner = MutableLiveData(false)
    val spinner = _spinner.toLiveData()

    private val _errorState = MutableLiveData<String?>()
    val errorState = _errorState.toLiveData()

    private val _ourBoards = MutableLiveData<List<Board>>()
    val ourBoards = _ourBoards.toLiveData()

    private val _userBoards = MutableLiveData<List<Board>>()
    val userBoards = _userBoards.toLiveData()

    private val _loadState = MutableLiveData<Boolean>()
    val loadState = _loadState.toLiveData()

    init {
        getFeed()
    }

    fun isLoading() = _loadState.value == true

    fun observeCompilation(
        compilation: Compilation,
        lifecycleOwner: LifecycleOwner,
        callback: (Compilation) -> Unit
    ) {
        when (compilation.slug) {
            Compilation.SLUG_POPULAR -> _popularCompilation.observe(lifecycleOwner, callback)
            Compilation.SLUG_VIDEO -> _videoCompilation.observe(lifecycleOwner, callback)
            else -> {
                _otherCompilations.value?.forEach { liveData ->
                    if (liveData.value?.slug == compilation.slug) {
                        liveData.observe(lifecycleOwner, callback)
                        return@forEach
                    }
                }
            }
        }
    }

    fun getCompilationWithOffset(compilation: Compilation?) {
        compilation?.let {
            if (it.isLast) return
            viewModelScope.launch {
                try {
                    _loadState.value = true
                    it.offset += ITEMS_PER_PAGE
                    setCompilation(repository.getCompilationWithOffset(it.slug, it.offset))
                } catch (error: Throwable) {
                    _errorState.value = error.message
                } finally {
                    _loadState.value = false
                }
            }
        }
    }

    private fun setCompilation(compilation: Compilation) {
        when (compilation.slug) {
            Compilation.SLUG_POPULAR -> setItems(_popularCompilation, compilation)
            Compilation.SLUG_VIDEO -> setItems(_videoCompilation, compilation)
            else -> {
                _otherCompilations.value?.forEach { liveData ->
                    if (liveData.value?.slug == compilation.slug) setItems(liveData, compilation)
                }
            }
        }
    }

    private fun setItems(mutableLiveData: MutableLiveData<Compilation>, compilation: Compilation) {
        mutableLiveData.value?.items?.addAll(compilation.items)
        mutableLiveData.value?.isLast = compilation.isLast
        mutableLiveData.value = mutableLiveData.value
    }

    private fun getFeed() {
        viewModelScope.launch {
            try {
                _spinner.value = true
                val feed = repository.getFeed()
                Log.d("TEST_VM", "feed ${feed.toString()}")
                _compilations.value = feed.compilations[0]
//        val feed = repository.getFakeFeed()
                setupCompilations(feed)
                setupBoards(feed)
            } catch (error: Throwable) {
                _errorState.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }

    private fun setupCompilations(feed: Feed) {
        val compilations = feed.compilations.toMutableList()
        val iterator = compilations.iterator()
        while (iterator.hasNext()) {
            val compilation = iterator.next()
            when (compilation.slug) {
                Compilation.SLUG_POPULAR -> {
                    _popularCompilation.value = compilation
                    iterator.remove()
                }
                Compilation.SLUG_VIDEO -> {
                    _videoCompilation.value = compilation
                    iterator.remove()
                }
            }
        }
        compilations.forEach {
            _otherCompilations.value?.add(MutableLiveData(it))
        }
        _otherCompilations.value = _otherCompilations.value
    }

    private fun setupBoards(feed: Feed) {
        val boards = feed.boards.toMutableList()
        val ourBoards = mutableListOf<Board>()
        boards.forEach {
            if (it.pos != null) ourBoards.add(it)
        }
        _ourBoards.value = ourBoards

        boards.removeAll(ourBoards)
        _userBoards.value = boards
    }

    companion object {
        const val ITEMS_PER_PAGE = 10
    }
}