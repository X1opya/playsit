package dev.playsit.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

data class FeedItem(
  @SerializedName("id")
  val id: Int,
  @SerializedName("name")
  val name: String,
  @SerializedName("slug")
  val slug: String?,
  @SerializedName("first_release_date")
  val firstReleaseDate: String?,
  @SerializedName("cover")
  val cover: String?,
  @SerializedName("genres")
  val genres: List<String>?,
  @SerializedName("platforms")
  val platforms: List<String>?,
  @SerializedName("publisher")
  val publisher: List<String>?,
  @SerializedName("developer")
  val developer: List<String>?,
  @SerializedName("involved_companies")
  val involvedCompanies: List<String>?,
  @SerializedName("added")
  val addedDate: String?,
  @SerializedName("duration")
  private val _duration: Long?,
  @SerializedName("video_identifier")
  val videoIdentifier: String?,
  @SerializedName("game_id")
  val gameId: Int?,
  @SerializedName("video_type")
  val videoType: String?,
  var isHeader: Boolean = false
) : Serializable {

  val formattedReleaseDate: String
    get() {
      firstReleaseDate?.let { dateString ->
        val date = parseFormat.parse(dateString)
        date?.let {
          return formatter.format(it)
        }
      }
      return ""
    }

  val formattedAddedDate: String
    get() {
      addedDate?.let { dateString ->
        val date = parseFormat.parse(dateString)
        date?.let {
          return formatter.format(it)
        }
      }
      return ""
    }

  val duration: String
    get() {
      _duration?.let {
        return when {
          it < 60L -> "0:$it"
          it == 60L -> "1:00"
          else -> {
            val minutes = TimeUnit.SECONDS.toMinutes(it)
            val seconds = it - minutes * 60
            val formattedSeconds = if (seconds < 10) "0$seconds" else seconds
            "${minutes}:${formattedSeconds}"
          }
        }
      }
      return ""
    }

  val uniquePlatforms: List<Platform>
    get() {
      val uniquePlatforms = mutableListOf<Platform>()
      platforms?.forEach { platform ->
        if (!uniquePlatforms.contains(Platform.PC) && (platform == "PC" || platform == "Windows Mixed Reality")) {
          uniquePlatforms += Platform.PC
        }
        if (!uniquePlatforms.contains(Platform.PS) && (platform == "PlayStation" || platform == "PlayStation 2" || platform == "PlayStation 3" || platform == "PlayStation 4" || platform == "PlayStation Portable" || platform == "PlayStation Network" || platform == "PlayStation Vita" || platform == "PlayStation VR" || platform == "PlayStation 5")) {
          uniquePlatforms += Platform.PS
        }
        if (!uniquePlatforms.contains(Platform.XBOX) && (platform == "Xbox" || platform == "Xbox 360" || platform == "Xbox One" || platform == "Xbox Project Scarlett" || platform == "Xbox Live Arcade" || platform == "Xbox Series X/S")) {
          uniquePlatforms += Platform.XBOX
        }
        if (!uniquePlatforms.contains(Platform.Stadia) && (platform == "Google Stadia")) {
          uniquePlatforms += Platform.Stadia
        }
        if (!uniquePlatforms.contains(Platform.Atari) && (platform == "Atari 2600" || platform == "Atari 7800" || platform == "Atari Lynx" || platform == "Atari Jaguar" || platform == "Atari ST/STE" || platform == "Atari 8-bit" || platform == "Atari 5200")) {
          uniquePlatforms += Platform.Atari
        }
        if (!uniquePlatforms.contains(Platform.Nintendo) && (platform == "Nintendo 64" || platform == "Nintendo Entertainment System (NES)" || platform == "Super Nintendo Entertainment System (SNES)" || platform == "Nintendo DS" || platform == "Nintendo GameCube" || platform == "Nintendo 3DS" || platform == "Virtual Console (Nintendo)" || platform == "Nintendo Switch" || platform == "New Nintendo 3DS" || platform == "Nintendo DSi" || platform == "Nintendo eShop")) {
          uniquePlatforms += Platform.Nintendo
        }
        if (!uniquePlatforms.contains(Platform.Apple) && (platform == "Apple II" || platform == "Apple IIGS" || platform == "Mac" || platform == "iOS")) {
          uniquePlatforms += Platform.Apple
        }
        if (!uniquePlatforms.contains(Platform.Android) && platform == "Android") {
          uniquePlatforms += Platform.Android
        }
        if (!uniquePlatforms.contains(Platform.Linux) && platform == "Linux") {
          uniquePlatforms += Platform.Linux
        }
        if (!uniquePlatforms.contains(Platform.Sega) && (platform == "Sega Mega Drive/Genesis" || platform == "Sega 32X" || platform == "Sega Saturn" || platform == "Sega Game Gear" || platform == "Sega Master System" || platform == "Sega CD" || platform == "SG-1000")) {
          uniquePlatforms += Platform.Sega
        }
        if (!uniquePlatforms.contains(Platform.Web) && platform == "Web browser") {
          uniquePlatforms += Platform.Web
        }
      }
      return uniquePlatforms
    }

  companion object {
    private val parseFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'")
    private val formatter = SimpleDateFormat("d MMMM")
  }
}

sealed class Platform {
  object PC : Platform()
  object PS : Platform()
  object XBOX : Platform()
  object Stadia : Platform()
  object Atari : Platform()
  object Nintendo : Platform()
  object Apple : Platform()
  object Android : Platform()
  object Linux : Platform()
  object Sega : Platform()
  object Web : Platform()
}