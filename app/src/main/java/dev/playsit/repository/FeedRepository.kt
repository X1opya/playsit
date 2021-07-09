package dev.playsit.repository

import android.util.Log
import dev.playsit.core.network.ApiService
import dev.playsit.dto.Compilation
import dev.playsit.dto.Feed
import dev.playsit.dto.FeedItem
import dev.playsit.dto.Game
import kotlinx.coroutines.delay

class FeedRepository(private val remoteDataSource: ApiService) {
    lateinit var feed: Feed

    suspend fun getFeed(): Feed {
        Log.d("TEST_FEED", "getFeed: method")
        val response = remoteDataSource.getFeed()
        if (response.isSuccessful) {
            response.body()?.let {
                feed = it
                return it
            }
        }
        throw Exception("Oops... error loading feed")
    }

    suspend fun getGameById(id: Int): Game {
        val response = remoteDataSource.getGameById(id)
        if (response.isSuccessful) {
            response.body()?.let { return it }
        }
        throw Exception("Oops... error loading game with id = $id")
    }

    suspend fun getSimilarGames(id: Int): List<FeedItem> {
        val response = remoteDataSource.getGameSimilarGames(id)
        if (response.isSuccessful) {
            response.body()?.let { return it }
        }
        throw Exception("Oops... error loading similar games with id = $id , msg = $")
    }

    suspend fun getCustomCompilation(slug: String, offset: Int): Compilation? {
        var result: Compilation? = null
        if (offset == 0) feed.compilations.forEach {
            if (it.slug == slug) result = it
        } else remoteDataSource.getCompilationWithOffset(slug, offset).body()?.let {
            result = it
        }

        return result
    }
}
