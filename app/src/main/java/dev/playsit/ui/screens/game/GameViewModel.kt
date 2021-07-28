package dev.playsit.ui.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.playsit.core.network.configurations.result.ApiResult
import dev.playsit.core.utils.toLiveData
import dev.playsit.dto.Game
import dev.playsit.dto.ReviewCollections
import dev.playsit.repository.FeedRepository
import dev.playsit.ui.screens.game.model.ReviewsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: FeedRepository,
    val reviewsUseCase: ReviewsUseCase
) : ViewModel() {

    private val _game = MutableLiveData<Game?>()
    val game = _game.toLiveData()

    var reviews: MutableSharedFlow<ApiResult<ReviewCollections>> = MutableSharedFlow()
    var isLoading = MutableStateFlow(true)

    fun getGameById(id: Int) {
        viewModelScope.launch {
            isLoading.emit(true)
            val game = repository.getGameById(id)
            game.similarGames = repository.getSimilarGames(id)
            _game.value = game
            loadReviews()
        }
    }

    private suspend fun loadReviews() {
        Log.d("TEST_FLOW", "loadReviews: ")
        Log.d("TEST_FLOW", "loadReviews: 2")

        reviewsUseCase.loadAsync(_game.value!!.id, 0)
            .collect {
                Log.d("TEST_FLOW", "loadReviews: collect ${it}")
                reviews.emit(it)
                isLoading.emit(false)
            }

    }

    fun onDestroy() {
        _game.value = null
    }
}
