package dev.playsit.ui.modules.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.playsit.core.utils.toLiveData
import dev.playsit.model.Game
import dev.playsit.repository.FeedRepository
import kotlinx.coroutines.launch

class GameViewModel(private val repository: FeedRepository) : ViewModel() {

    private val _game = MutableLiveData<Game?>()
    val game = _game.toLiveData()

    fun getGameById(id: Int) {
        viewModelScope.launch {
            val game = repository.getGameById(id)
            game.similarGames = repository.getSimilarGames(id)
            _game.value = game
        }
    }

    fun onDestroy() {
        _game.value = null
    }
}