package dev.playsit.dto

import com.google.gson.annotations.SerializedName

data class Similar(
    @SerializedName("similar")
    val similar: List<FeedItem>
)