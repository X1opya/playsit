package dev.playsit.dto

import com.google.gson.annotations.SerializedName
import dev.playsit.R

data class Game(
    @SerializedName("age_rating")
    val ageRating: String?,
    @SerializedName("aggregated_rating")
    val aggregatedRating: Float,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("aggregated_rating_count")
    val aggregatedRatingCount: Float,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("developer")
    val developer: List<String>,
    @SerializedName("first_release_date")
    val firstReleaseDate: String?,
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
    @SerializedName("dlc")
    val dlcList: List<Dlc>?,
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

    val uniquePlatforms: List<Platform>
        get() {
            val uniquePlatforms = mutableListOf<Platform>()
            platforms.forEach { platform ->
                if (!uniquePlatforms.contains(Platform.PC) && (platform == "PC" ||
                            platform == "Windows Mixed Reality")
                ) {
                    uniquePlatforms += Platform.PC
                }
                if (!(uniquePlatforms.contains(Platform.PS) || !(platform == "PlayStation" ||
                            platform == "PlayStation 2" || platform == "PlayStation 3" ||
                            platform == "PlayStation 4" || platform == "PlayStation Portable" ||
                            platform == "PlayStation Network" || platform == "PlayStation Vita" ||
                            platform == "PlayStation VR" || platform == "PlayStation 5"))
                ) {
                    uniquePlatforms += Platform.PS
                }
                if (!uniquePlatforms.contains(Platform.XBOX) && (platform == "Xbox" ||
                            platform == "Xbox 360" || platform == "Xbox One" ||
                            platform == "Xbox Project Scarlett" ||
                            platform == "Xbox Live Arcade" || platform == "Xbox Series X/S")
                ) {
                    uniquePlatforms += Platform.XBOX
                }
                if (!uniquePlatforms.contains(Platform.Stadia) && (platform == "Google Stadia")) {
                    uniquePlatforms += Platform.Stadia
                }
                if (!uniquePlatforms.contains(Platform.Atari) && (platform == "Atari 2600" ||
                            platform == "Atari 7800" || platform == "Atari Lynx" ||
                            platform == "Atari Jaguar" || platform == "Atari ST/STE" ||
                            platform == "Atari 8-bit" || platform == "Atari 5200")
                ) {
                    uniquePlatforms += Platform.Atari
                }
                if (!uniquePlatforms.contains(Platform.Nintendo) && (platform == "Nintendo 64" ||
                            platform == "Nintendo Entertainment System (NES)" ||
                            platform == "Super Nintendo Entertainment System (SNES)" ||
                            platform == "Nintendo DS" || platform == "Nintendo GameCube" ||
                            platform == "Nintendo 3DS" ||
                            platform == "Virtual Console (Nintendo)" ||
                            platform == "Nintendo Switch" || platform == "New Nintendo 3DS" ||
                            platform == "Nintendo DSi" || platform == "Nintendo eShop")
                ) {
                    uniquePlatforms += Platform.Nintendo
                }
                if (!uniquePlatforms.contains(Platform.Apple) && (platform == "Apple II" ||
                            platform == "Apple IIGS" || platform == "Mac" || platform == "iOS")
                ) {
                    uniquePlatforms += Platform.Apple
                }
                if (!uniquePlatforms.contains(Platform.Android) && platform == "Android") {
                    uniquePlatforms += Platform.Android
                }
                if (!uniquePlatforms.contains(Platform.Linux) && platform == "Linux") {
                    uniquePlatforms += Platform.Linux
                }
                if (!uniquePlatforms.contains(Platform.Sega) &&
                    (platform == "Sega Mega Drive/Genesis" ||
                            platform == "Sega 32X" || platform == "Sega Saturn" ||
                            platform == "Sega Game Gear" || platform == "Sega Master System" ||
                            platform == "Sega CD" || platform == "SG-1000")
                ) {
                    uniquePlatforms += Platform.Sega
                }
                if (!uniquePlatforms.contains(Platform.Web) && platform == "Web browser") {
                    uniquePlatforms += Platform.Web
                }
            }
            return uniquePlatforms
        }
}

object GameImageConverter {
    fun getGenreIdList(items: List<String>) = items.map { bind(it) }
    fun getPlatformIdList(items: List<Platform>) = items.map { bind(it) }

    fun getStoreIcon(name: String?) =
        when (name) {
            "Xbox" -> R.drawable.ic_xbox_store
            "Steam" -> R.drawable.ic_steam_store
            else -> R.drawable.ic_ps_store
        }

    private fun bind(item: String): Int {
        return when (item) {
            Game.GENRE_ADVENTURE -> R.drawable.ic_adventure
            Game.GENRE_ARCADE -> R.drawable.ic_arcade
            Game.GENRE_FIGHTING -> R.drawable.ic_fighting
            Game.GENRE_INDIE -> R.drawable.ic_indie
            Game.GENRE_MUSIC -> R.drawable.ic_music
            Game.GENRE_PINBALL -> R.drawable.ic_pinball
            Game.GENRE_PLATFORM -> R.drawable.ic_platform
            Game.GENRE_POINT_AND_CLICK -> R.drawable.ic_point_and_click
            Game.GENRE_PUZZLE -> R.drawable.ic_puzzle
            Game.GENRE_QUIZ -> R.drawable.ic_quiz
            Game.GENRE_RACING -> R.drawable.ic_racing
            Game.GENRE_RPG -> R.drawable.ic_rpg
            Game.GENRE_RTS -> R.drawable.ic_rts
            Game.GENRE_SHOOTER -> R.drawable.ic_shooter
            Game.GENRE_SIMULATOR -> R.drawable.ic_simulator
            Game.GENRE_SLASHER -> R.drawable.ic_slasher
            Game.GENRE_SPORT -> R.drawable.ic_sport
            Game.GENRE_STRATEGY -> R.drawable.ic_strategy
            Game.GENRE_TACTICAL -> R.drawable.ic_tactical
            else -> R.drawable.ic_tbs
        }
    }

    private fun bind(item: Platform): Int {
        return when (item) {
            Platform.PC -> R.drawable.ic_pc
            Platform.PS -> R.drawable.ic_ps
            Platform.XBOX -> R.drawable.ic_xbox
            Platform.Stadia -> R.drawable.ic_stadia
            Platform.Apple -> R.drawable.ic_apple
            Platform.Android -> R.drawable.ic_android
            Platform.Linux -> R.drawable.ic_linux
            Platform.Nintendo -> R.drawable.ic_nintendo
            Platform.Sega -> R.drawable.ic_sega
            Platform.Atari -> R.drawable.ic_atari
            Platform.Web -> R.drawable.ic_globe
        }
    }
}
