package dev.playsit.model

import com.google.gson.annotations.SerializedName

data class Prices(
    @SerializedName("last_updated_time")
    val lastUpdatedTime: String,
    @SerializedName("stores")
    val stores: List<Store>
)

data class Store(
    @SerializedName("steam")
    val steam: Steam?,
    @SerializedName("type")
    val type: String,
    @SerializedName("xbox_store")
    val xboxStore: XboxStore?,
    @SerializedName("ps_store")
    val psStore: PsStore?
)

data class XboxStore(
    @SerializedName("price")
    val price: String,
    @SerializedName("url")
    val url: String
)

data class Steam(
    @SerializedName("price")
    val price: String,
    @SerializedName("url")
    val url: String
)

data class PsStore(
    @SerializedName("price")
    val price: String,
    @SerializedName("url")
    val url: String
)