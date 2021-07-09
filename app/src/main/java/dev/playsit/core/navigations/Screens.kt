package dev.playsit.core.navigations

import dev.playsit.dto.FeedItem

object Screens {
    fun toGameScreen(feedItem: FeedItem): String {
        return "GameDetail/${feedItem.id}"
    }
    fun toVideoList() = "VideoList"
    fun toGameList(index: Int) = "GameList/$index"
}
