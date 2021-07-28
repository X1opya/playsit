package dev.playsit.ui.screens.game.components

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import dev.playsit.R
import dev.playsit.core.navigations.Screens
import dev.playsit.core.navigations.navigateToGameScreen
import dev.playsit.dto.FeedItem
import dev.playsit.ui.components.gameCards.SmallGameCard
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.theme.BaseAppDimen

@Composable
fun SimilarGameSection(games: List<FeedItem?>, navController: NavHostController) {
    Spacer(modifier = Modifier.size(BaseAppDimen))
    CategoryTitleText(
        text = stringResource(id = R.string.similarGames),
        Modifier.padding(start = BaseAppDimen)
    )
    Spacer(modifier = Modifier.size(BaseAppDimen))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BaseAppDimen),
        state = rememberLazyListState()
    ) {
        itemsIndexed(games) { index, value ->
            if (index == 0) Spacer(modifier = Modifier.padding(start = BaseAppDimen))
            value?.let {
                SmallGameCard(
                    feedItem = value
                ) {
                    navController.navigateToGameScreen(it)
                }
            }
        }
    }
}
