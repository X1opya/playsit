package dev.playsit.ui.modules.feed

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.playsit.core.utils.toLiveData
import dev.playsit.dto.Board
import dev.playsit.dto.Feed
import dev.playsit.repository.FeedRepository
import dev.playsit.ui.modules.feed.compilations.CompilationProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val repository: FeedRepository) : ViewModel() {

    private val _compilations = MutableLiveData<MutableList<CompilationProvider>>()
    val compilations: LiveData<MutableList<CompilationProvider>> = _compilations

    private val _ourBoards = MutableLiveData<List<Board>>()
    val ourBoards = _ourBoards.toLiveData()

    private val _userBoards = MutableLiveData<List<Board>>()
    val userBoards = _userBoards.toLiveData()

    val _isLoading = MutableStateFlow(true)
    val isFeedLoaded = MutableStateFlow(true)

    init {
//        getFeed()
    }

    val isLoading = _isLoading.asStateFlow()

    fun getFeed() {
        viewModelScope.launch {
            try {
                _isLoading.emit(true)
                isFeedLoaded.value = true
                val feed = repository.getFeed()
                setupCompilations(feed)
                setupBoards(feed)
            } catch (error: Throwable) {
//                _errorState.value = error.message
            } finally {
                isFeedLoaded.value = false
//                _spinner.value = false
            }
        }
    }

    private suspend fun setupCompilations(feed: Feed) {
        val list = feed.compilations.toMutableList()
        var providerList = mutableListOf<CompilationProvider>()
        list.forEach {
            val provider = CompilationProvider(it, repository, this)
            providerList.add(provider)
        }
        _compilations.value = providerList
    }

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
