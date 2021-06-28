package dev.playsit.model

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("compilations")
    val compilations: List<Compilation>,
    @SerializedName("boards")
    val boards: List<Board>
)