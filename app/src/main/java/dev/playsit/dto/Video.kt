package dev.playsit.dto

import com.google.gson.annotations.SerializedName
import dev.playsit.core.utils.millisecondsToTime
import dev.playsit.core.utils.toReleaseDate

data class Video(
    @SerializedName("id")
    val id: Int,
    @SerializedName("added")
    val addedDate: String,
    @SerializedName("name")
    override val name: String,
    @SerializedName("duration")
    val _duration: Long,
    @SerializedName("video_identifier")
    override val videoIdentifier: String,
    @SerializedName("game_id")
    val gameId: Int,
    @SerializedName("cover")
    override val cover: String,
    @SerializedName("video_type")
    val videoType: String?
) : VideoDto {
    override val duration: String
        get() = _duration.millisecondsToTime()
    override val formattedAddedDate: String
        get() = addedDate.toReleaseDate()
}
