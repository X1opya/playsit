package dev.playsit.model

import com.google.gson.annotations.SerializedName
import java.util.concurrent.TimeUnit

data class GameVideo(
    @SerializedName("added")
    val added: String,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("duration")
    private val _duration: Long,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: Any,
    @SerializedName("video_identifier")
    val videoIdentifier: String
) {
    val duration: String
        get() {
            _duration.let {
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
}