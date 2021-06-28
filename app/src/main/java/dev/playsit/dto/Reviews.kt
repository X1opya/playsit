package dev.playsit.dto

import com.google.gson.annotations.SerializedName

data class ReviewCollections(
    @SerializedName("positive_count") val positiveCount: Int,
    @SerializedName("mixed_count") val mixedCount: Int,
    @SerializedName("negative_count") val negativeCount: Int,
    @SerializedName("reviews") val reviews: List<Review>,
)

data class Review(
    @SerializedName("id") val id: Int,
    @SerializedName("gameId") val gameId: Int,
    @SerializedName("review_text") val reviewText: String,
    @SerializedName("review_score") val reviewScore: Int,
    @SerializedName("date_added") val dateAdded: String,
    @SerializedName("likes_count") val likesCount: Int,
    @SerializedName("user") val user: User,
    @SerializedName("replies") val replies: Any,
)
