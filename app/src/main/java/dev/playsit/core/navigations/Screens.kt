package dev.playsit.core.navigations

import android.os.Bundle
import androidx.navigation.NavController
import dev.playsit.dto.FeedItem

object Screens {
    fun toGameScreen(feedItem: FeedItem) ="GameDetail/${feedItem.id}"
    fun toVideoList() = "VideoList"
    fun toGameList(index: Int) = "GameList/$index"
}

fun NavController.navigateToGameScreen(game: FeedItem) {
    this.currentBackStackEntry?.arguments =
        Bundle().apply {
            putSerializable("game", game)
        }
    this.navigate(Screens.toGameScreen(game))
}
