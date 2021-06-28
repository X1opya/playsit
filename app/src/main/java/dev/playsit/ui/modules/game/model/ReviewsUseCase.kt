package dev.playsit.ui.modules.game.model

import androidx.lifecycle.asLiveData
import javax.inject.Inject

class ReviewsUseCase @Inject constructor(private val repository: GameRepository) {

    fun loadAsync(id: Int, offset: Int) = repository.loadReviewsFlow(id, offset)
}
