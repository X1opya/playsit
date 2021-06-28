package dev.playsit.dto

import com.google.gson.annotations.SerializedName

data class Board(
    @SerializedName("access_type")
    val accessType: String,
    @SerializedName("cover")
    override val cover: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("likes_count")
    override val likesCount: Int,
    @SerializedName("owner_id")
    val ownerId: Int,
    @SerializedName("owner_nickname")
    override val ownerNickname: String,
    @SerializedName("pos")
    val pos: Int?,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("small_info")
    override val smallInfo: String,
    @SerializedName("subtitle")
    override val subtitle: String,
    @SerializedName("title")
    override val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("items")
    override val items: List<Game>?
) : OurBord, UserBoard

interface UserBoard {
    val items: List<Game?>?
    val likesCount: Int
    val title: String
    val ownerNickname: String
}

interface OurBord {
    val cover: String
    val title: String
    val subtitle: String
    val smallInfo: String?
}
