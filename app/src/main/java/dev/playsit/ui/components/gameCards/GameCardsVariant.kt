package dev.playsit.ui.components.gameCards

import androidx.compose.runtime.Composable
import dev.playsit.model.FeedItem
import dev.playsit.ui.components.imageContainers.ImageType

@Composable
fun SmallGameCard(feedItem: FeedItem, onClick: (id: Int) -> Unit) {
    GameCard(feedItem = feedItem, cardType = ImageType.Small, false, onClick)
}

@Composable
fun BigGameCard(feedItem: FeedItem, onClick: (id: Int) -> Unit) {
    GameCard(feedItem = feedItem, cardType = ImageType.Big, true, onClick)
}
