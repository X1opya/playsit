package dev.playsit.model

import com.google.gson.annotations.SerializedName

data class Board(
    @SerializedName("access_type")
    val accessType: String,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("owner_id")
    val ownerId: Int,
    @SerializedName("owner_nickname")
    val ownerNickname: String,
    @SerializedName("pos")
    val pos: Int?,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("small_info")
    val smallInfo: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("items")
    val items: List<Game>?
)