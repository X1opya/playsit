package dev.playsit.dto

import com.google.gson.annotations.SerializedName

data class Prices(
    @SerializedName("last_updated_time")
    val lastUpdatedTime: String,
    @SerializedName("stores")
    val stores: List<PlatformStore>
)

data class PlatformStore(
    @SerializedName("steam")
    private val steam: Store?,
    @SerializedName("type")
    val type: String,
    @SerializedName("xbox_store")
    private val xboxStore: Store?,
    @SerializedName("ps_store")
    private val psStore: Store?
) {
    val store get() = steam ?: xboxStore ?: psStore
}

data class Store(
    @SerializedName("price")
    val price: String,
    @SerializedName("discount_price")
    val discount: String?,
    @SerializedName("url")
    val url: String
)
