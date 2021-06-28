package dev.playsit.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Compilation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("is_last")
    var isLast: Boolean,
    @SerializedName("items")
    val items: MutableList<FeedItem>,
    var offset: Int = 0
) : Serializable {
    companion object {
        const val SLUG_POPULAR = "popular"
        const val SLUG_VIDEO = "videos"
        const val SLUG_UPCOMING = "upcoming"
        const val SLUG_LATEST = "latest"
    }
}