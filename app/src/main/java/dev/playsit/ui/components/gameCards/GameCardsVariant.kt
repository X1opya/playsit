package dev.playsit.ui.components.gameCards

import android.util.Log
import androidx.compose.runtime.Composable
import dev.playsit.dto.FeedItem
import dev.playsit.dto.GameCardDto
import dev.playsit.ui.components.imageContainers.ImageType
import dev.playsit.ui.theme.PlaceHolderDefaultLoading

@Composable
fun SmallGameCard(
    feedItem: GameCardDto?,
    loading: Boolean = PlaceHolderDefaultLoading,
    onClick: () -> Unit
) {
    GameCard(
        feedItem = feedItem,
        cardType = ImageType.Small,
        false,
        loading = loading,
        onClick = onClick
    )
}

@Composable
fun BigGameCard(
    feedItem: GameCardDto?,
    loading: Boolean = PlaceHolderDefaultLoading,
    onClick: () -> Unit
) {
    Log.d("TEST_CARD", "BigGameCard")
    GameCard(
        feedItem = feedItem,
        cardType = ImageType.Big,
        true,
        loading = loading,
        onClick = onClick
    )
}
