package dev.playsit.core.navigations

import com.google.gson.Gson
import com.squareup.moshi.Moshi
import dev.playsit.dto.FeedItem

object Screens {
    fun toGameScreen(feedItem: FeedItem): String {
        return "GameDetail/${feedItem.id}"
    }
}
