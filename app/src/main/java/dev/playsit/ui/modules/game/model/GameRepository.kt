package dev.playsit.ui.modules.game.model

import android.util.Log
import dev.playsit.core.network.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameRepository @Inject constructor(private val service: ApiService) {
//    fun loadReviews(gameId: Int, offset: Int) =
//        service.loadReviews(gameId, offset)

    fun loadReviewsFlow(gameId: Int, offset: Int) = flow {
        Log.d("TEST_FLOW", "loadReviewsFlow: ")
        try {
            emit(service.loadReviews(gameId, offset))
        } catch (e: Exception) {}
    }
}
