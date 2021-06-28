package dev.playsit.ui.modules.feed

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.playsit.core.utils.toLiveData
import dev.playsit.dto.Board
import dev.playsit.dto.Compilation
import dev.playsit.dto.Feed
import dev.playsit.repository.FeedRepository
import dev.playsit.ui.modules.feed.compilations.CompilationProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val repository: FeedRepository) : ViewModel() {

    private val _compilations = MutableLiveData<MutableList<CompilationProvider>>()
    val compilations: LiveData<MutableList<CompilationProvider>> = _compilations

    private val _feed = flow<Feed> { repository.getFeed() }
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

    val _isLoading = MutableStateFlow<Boolean>(false)
//    val loadState by _loadState.collectAsState()

    init {
//        getFeed()
    }

    fun isLoading() = _isLoading.value == true

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
                    _isLoading.emit(true)
                    it.offset += ITEMS_PER_PAGE
                    val resp = repository.getCompilationWithOffset(it.slug, it.items.size)
//                    _compilations.value?.items?.addAll(resp.items)

//                    setCompilation(repository.getCompilationWithOffset(it.slug, it.items.size))
                } catch (error: Throwable) {
                    _errorState.value = error.message
                } finally {
                    _isLoading.emit(false)
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

    fun getFeed() {
        viewModelScope.launch {
            try {
                _isLoading.emit(true)
                _spinner.value = true
                val feed = repository.getFeed()
//                _feed.emit(feed)
//                _compilations.value = feed.compilations[0]
//        val feed = repository.getFakeFeed()
                setupCompilations(feed)
                setupBoards(feed)
            } catch (error: Throwable) {
                _errorState.value = error.message
            } finally {

                _isLoading.emit(false)
                _spinner.value = false
            }
        }
    }

    private fun setupCompilations(feed: Feed) {
        val list = feed.compilations.toMutableList()
        var providerList = mutableListOf<CompilationProvider>()
        list.forEach {
            providerList.add(CompilationProvider(it, repository))
        }
        _compilations.value = providerList
//        val iterator = compilations.iterator()
//        while (iterator.hasNext()) {
//            val compilation = iterator.next()
//            when (compilation.slug) {
//                Compilation.SLUG_POPULAR -> {
//                    _popularCompilation.value = compilation
//                    iterator.remove()
//                }
//                Compilation.SLUG_VIDEO -> {
//                    _videoCompilation.value = compilation
//                    iterator.remove()
//                }
//            }
    }
//        compilations.forEach {
////            _otherCompilations.value?.add(MutableLiveData(CompilationProvider(it, repository)))
//        }
//        _otherCompilations.value = _otherCompilations.value
//    }

    private fun setupBoards(feed: Feed) {
        val boards = feed.boards.toMutableList()
        val ourBoards = mutableListOf<Board>()
        val userBoards = mutableListOf<Board>()
        boards.forEach {
            if (it.pos != null) ourBoards.add(it)
            else userBoards.add(it)
        }
        _ourBoards.value = ourBoards
        _userBoards.value = userBoards
    }

    companion object {
        const val ITEMS_PER_PAGE = 10
    }
}
