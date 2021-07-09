package dev.playsit.dto

import com.google.gson.annotations.SerializedName
import dev.playsit.core.utils.millisecondsToTime
import dev.playsit.core.utils.toReleaseDate
import java.io.Serializable
import java.util.concurrent.TimeUnit

data class GameVideo(
    @SerializedName("added")
    val added: String,
    @SerializedName("cover")
    override val cover: String,
    @SerializedName("duration")
    private val _duration: Long,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    override val name: String,
    @SerializedName("type")
    val type: Any,
    @SerializedName("video_identifier")
    override val videoIdentifier: String
) : Serializable, VideoDto {
    override val duration: String
        get() = _duration.millisecondsToTime()
    override val formattedAddedDate: String
        get() = added.toReleaseDate()
}
