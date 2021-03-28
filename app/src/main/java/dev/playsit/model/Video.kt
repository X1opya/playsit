package dev.playsit.model

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: Int,
    @SerializedName("added")
    val addedDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("duration")
    val duration: Long,
    @SerializedName("video_identifier")
    val videoIdentifier: String,
    @SerializedName("game_id")
    val gameId: Int,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("video_type")
    val videoType: String?
)