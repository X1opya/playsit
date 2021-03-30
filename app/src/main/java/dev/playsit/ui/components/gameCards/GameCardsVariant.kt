package dev.playsit.ui.components.gameCards

import androidx.compose.runtime.Composable
import dev.playsit.model.FeedItem
import dev.playsit.ui.components.imageContainers.ImageType

@Composable
fun SmallGameCard(feedItem: FeedItem) {
    GameCard(feedItem = feedItem, cardType = ImageType.Small, false)
}

@Composable
fun BigGameCard(feedItem: FeedItem) {
    GameCard(feedItem = feedItem, cardType = ImageType.Big, false)
}

