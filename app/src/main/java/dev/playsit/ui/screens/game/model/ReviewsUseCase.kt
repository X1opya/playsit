package dev.playsit.ui.screens.game.model

import javax.inject.Inject

class ReviewsUseCase @Inject constructor(private val repository: GameRepository) {

    fun loadAsync(id: Int, offset: Int) = repository.loadReviewsFlow(id, offset)
}
