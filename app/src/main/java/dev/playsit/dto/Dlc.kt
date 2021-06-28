package dev.playsit.dto

import com.google.gson.annotations.SerializedName

data class Dlc(
    @SerializedName("id") override val id: Int,
    @SerializedName("name") override val name: String?,
    @SerializedName("cover") override val cover: String?
) : GameCardDto {
    override val genres: List<String>?
        get() = null
}
