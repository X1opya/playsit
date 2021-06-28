package dev.playsit.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("profile_image") val profileImage: String?,
    @SerializedName("bio") val bio: Any,
    @SerializedName("status") val status: Any,
    @SerializedName("quiz_points") val quizPoints: Int?,
    @SerializedName("followers_count") val followersCount: Int?,
    @SerializedName("following_count") val followingCount: Int?,
    @SerializedName("rank") val rank: Int?,
    @SerializedName("user_score") val userScore: Int?,
    @SerializedName("is_premium") val isPremium: Boolean?,
    @SerializedName("is_private") val isPrivate: Boolean?,
    @SerializedName("social_links") val socialLinks: Map<String, String?>
)
