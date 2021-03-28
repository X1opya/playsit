package dev.playsit.model

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("age_rating")
    val ageRating: String,
    @SerializedName("aggregated_rating")
    val aggregatedRating: Any,
    @SerializedName("aggregated_rating_count")
    val aggregatedRatingCount: Any,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("developer")
    val developer: List<String>,
    @SerializedName("first_release_date")
    val firstReleaseDate: String,
    @SerializedName("game_modes")
    val gameModes: List<String>,
    @SerializedName("game_videos")
    val gameVideos: List<GameVideo>?,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_view")
    val lastView: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("platforms")
    val platforms: List<String>,
    @SerializedName("played_count")
    val playedCount: Int,
    @SerializedName("prices")
    val prices: Prices?,
    @SerializedName("publisher")
    val publisher: List<String>,
    @SerializedName("rating_count")
    val ratingCount: Int,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    @SerializedName("screenshots")
    val screenshots: List<String>?,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("views")
    val views: Int,
    @SerializedName("will_play_count")
    val willPlayCount: Int,
    var similarGames: List<FeedItem>? = null
) {
    companion object {
        const val GENRE_ARCADE = "Arcade"
        const val GENRE_SIMULATOR = "Simulator"
        const val GENRE_INDIE = "Indie"
        const val GENRE_RPG = "RPG"
        const val GENRE_ADVENTURE = "Adventure"
        const val GENRE_RTS = "RTS"
        const val GENRE_PINBALL = "Pinball"
        const val GENRE_RACING = "Racing"
        const val GENRE_QUIZ = "Quiz"
        const val GENRE_PUZZLE = "Puzzle"
        const val GENRE_SLASHER = "Slasher"
        const val GENRE_PLATFORM = "Platform"
        const val GENRE_TACTICAL = "Tactical"
        const val GENRE_MUSIC = "Music"
        const val GENRE_TBS = "TBS"
        const val GENRE_SHOOTER = "Shooter"
        const val GENRE_STRATEGY = "Strategy"
        const val GENRE_FIGHTING = "Fighting"
        const val GENRE_SPORT = "Sport"
        const val GENRE_POINT_AND_CLICK = "Point & Click"
    }
}