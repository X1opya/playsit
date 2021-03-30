package dev.playsit.repository

import android.util.Log
import dev.playsit.core.network.ApiService
import dev.playsit.model.Compilation
import dev.playsit.model.Feed
import dev.playsit.model.FeedItem
import dev.playsit.model.Game
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

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
            response.body()?.let { return it.similar }
        }
        throw Exception("Oops... error loading similar games with id = $id")
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

    suspend fun getCompilationWithOffset(slug: String, offset: Int): Compilation {
        val response = remoteDataSource.getCompilationWithOffset(slug, offset)
        if (response.isSuccessful) {
            response.body()?.let { return it }
        }
        throw Exception("Oops... error loading compilation with slug = $slug")
    }

    suspend fun getFakeFeed(): Feed {
        delay(500)
        val games = mutableListOf<FeedItem>()
        for (i in 0..1) {
            val game = if (i % 2 == 0) {
                FeedItem(
                    id = i,
                    name = "Cyberpunk 2077",
                    slug = "Cyberpunk 2077",
                    firstReleaseDate = "",
                    cover = "https://ixbt.online/gametech/games/2020/12/28/nBuKotYCHMN1os70XxVVVkTFy2JI7X9Alaf0Fbpp.jpg",
                    genres = listOf("Action", "RPG"),
                    platforms = listOf("PC"),
                    publisher = listOf("CD PROJEKT RED"),
                    developer = listOf("CD PROJEKT RED"),
                    involvedCompanies = null,
                    addedDate = "",
                    _duration = null,
                    videoIdentifier = null,
                    gameId = null,
                    videoType = null
                )
            } else {
                FeedItem(
                    id = i,
                    name = "The Witcher 3: Wild Hunt",
                    slug = "The Witcher 3: Wild Hunt",
                    firstReleaseDate = "",
                    cover = "https://image.api.playstation.com/vulcan/img/rnd/202009/2913/TQKAd8U6hnIFQIIcz6qnFh8C.png",
                    genres = listOf("Action", "RPG"),
                    platforms = listOf("PC"),
                    publisher = listOf("CD PROJEKT RED"),
                    developer = listOf("CD PROJEKT RED"),
                    involvedCompanies = null,
                    addedDate = "",
                    _duration = null,
                    videoIdentifier = null,
                    gameId = null,
                    videoType = null
                )
            }
            games.add(game)
        }
        return Feed(
            listOf(
                Compilation(
                    1,
                    "Popular",
                    "Most trending games",
                    "popular",
                    "game",
                    true,
                    games
                )
            ), listOf()
        )
    }
}