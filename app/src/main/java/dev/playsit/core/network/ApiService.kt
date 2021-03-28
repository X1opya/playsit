package dev.playsit.core.network

import dev.playsit.model.Compilation
import dev.playsit.model.Feed
import dev.playsit.model.Game
import dev.playsit.model.Similar
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

  @GET("feed")
  suspend fun getFeed(): Response<Feed>

  @GET("feed/compilations/{slug}")
  suspend fun getCompilationWithOffset(
    @Path("slug") slug: String,
    @Query("offset") offset: Int
  ): Response<Compilation>

  @GET("games/{id}")
  suspend fun getGameById(@Path("id") id: Int): Response<Game>

  @GET("games/{id}/similar_games")
  suspend fun getGameSimilarGames(@Path("id") id: Int): Response<Similar>
}