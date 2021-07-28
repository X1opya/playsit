package dev.playsit.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.playsit.core.network.configurations.result.ApiResult
import dev.playsit.core.network.configurations.result.isSuccess
import dev.playsit.dto.*
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.components.text.DefaultGrayText
import dev.playsit.ui.screens.game.components.*
import dev.playsit.ui.theme.*

@Composable
fun GameDetail(game: FeedItem, navController: NavHostController) {
    val gameViewModel: GameViewModel = hiltViewModel()
    val fullGame by gameViewModel.game.observeAsState()
    DisposableEffect(key1 = game.id) {
        gameViewModel.getGameById(game.id)
        onDispose {}
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LaunchScreen(game, fullGame, gameViewModel, navController)
    }
}

@Composable
private fun LaunchScreen(
    game: FeedItem,
    fullGame: Game?,
    gameViewModel: GameViewModel,
    navController: NavHostController
) {
    val loading = gameViewModel.isLoading.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        game.gameVideos?.get(0)?.videoIdentifier?.let {
            BackPlayerView(it)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 25.dp)
        ) {
            GameImageCard(
                uri = game.cover,
                rating = fullGame?.ratingCount?.toString() ?: fullGame?.rating?.toString() ?: "n/a",
                loading = loading.value
            )
            Spacer(modifier = Modifier.padding(top = 25.dp))
            CategoryTitleText(
                text = game.name,
                maxLines = 3,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = 5.dp))
            DefaultGrayText(text = game.developer?.get(0) ?: "")
            game.uniquePlatforms.let {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                GamePlatformIcons(it)
            }
            Spacer(modifier = Modifier.size(30.dp))
            UserActionPanel()
            Spacer(modifier = Modifier.size(25.dp))
            GameReviewAndRating(
                fullGame?.reviewsCount.toString(),
                fullGame?.aggregatedRating?.toInt().toString(),
                object : OnReviewAndRatingClick {
                    override fun onRatingClick() {}
                    override fun onReviewClick() {}
                }, loading.value
            )
        }
        val reviews = gameViewModel.reviews.collectAsState(initial = null)
        if (reviews.value?.isSuccess == true) {
            val value = (reviews.value as ApiResult.Success<ReviewCollections>).value!!
            ReviewLazyList(value)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 25.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .height(1.dp)
                    .background(DividerColor)
            )
        }
        fullGame?.let {
            Column {
                GameInfoSection(game = fullGame)
                fullGame.prices?.let {
                Spacer(modifier = Modifier.size(25.dp))
                    StoreSection(it)
                }
            }
            fullGame.dlcList?.let { DlcSection(it) }
            fullGame.gameVideos?.takeIf { it.isNotEmpty() }?.let { VideSectionList(it) }
            Spacer(modifier = Modifier.size(40.dp))
            fullGame.similarGames?.let {
                Column(Modifier.background(UnSelectTabColor)) {
                    SimilarGameSection(games = it, navController)
                    Spacer(modifier = Modifier.size(BaseAppDimen))
                }
            }
        }
    }
}

@Composable
fun GamePlatformIcons(platforms: List<Platform>) {
    Row {
        val list = GameImageConverter
            .getPlatformIdList(platforms)
        list.forEachIndexed { index, item ->
            Icon(
                painter = painterResource(id = item),
                null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(20.dp)
            )
            if (index != list.size - 1) Spacer(modifier = Modifier.size(12.dp))
        }
    }
}
