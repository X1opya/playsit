package dev.playsit.ui.modules.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
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
import dev.playsit.dto.Game
import dev.playsit.dto.GameImageConverter
import dev.playsit.dto.ReviewCollections
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.components.text.DefaultGrayText
import dev.playsit.ui.modules.game.components.*
import dev.playsit.ui.theme.BaseAppDimen
import dev.playsit.ui.theme.DividerColor
import dev.playsit.ui.theme.UnSelectTabColor

@Composable
fun GameDetail(id: Int, navController: NavHostController) {
    val gameViewModel: GameViewModel = hiltViewModel()
    val game by gameViewModel.game.observeAsState()
    val loading = gameViewModel.isLoading.collectAsState()
    DisposableEffect(key1 = id) {
        gameViewModel.getGameById(id)
        onDispose {
            gameViewModel.onDestroy()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (loading.value) CircularProgressIndicator(modifier = Modifier.background(Color.Green))
        game?.let { LaunchScreen(it, gameViewModel, navController) }
    }
}

@Composable
private fun LaunchScreen(
    game: Game,
    gameViewModel: GameViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 25.dp)
        ) {
            Spacer(modifier = Modifier.padding(top = 224.dp))
            GameImageCard(
                uri = game.cover,
                rating = game.ratingCount.toString() ?: game?.rating?.toString()
                ?: "n/a"
            )
            Spacer(modifier = Modifier.padding(top = 25.dp))
            CategoryTitleText(
                text = game.name ?: "",
                maxLines = 3,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = 5.dp))
            DefaultGrayText(text = game.developer[0] ?: "")
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row {
                val list = GameImageConverter
                    .getPlatformIdList(game.uniquePlatforms ?: emptyList())
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
            Spacer(modifier = Modifier.size(30.dp))
            UserActionPanel()
            Spacer(modifier = Modifier.size(25.dp))
            GameReviewAndRating(
                game.reviewsCount.toString(),
                game.aggregatedRating.toInt().toString(),
                object : OnReviewAndRatingClick {
                    override fun onRatingClick() {}
                    override fun onReviewClick() {}
                })
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
        Column {
            GameInfoSection(game = game)
            game.prices?.let { StoreSection(it) }
        }
        game.dlcList?.let { DlcSection(it) }
        game.gameVideos?.takeIf { it.isNotEmpty() }?.let { VideSectionList(it) }
        Spacer(modifier = Modifier.size(40.dp))
        game.similarGames?.let {
            Column(Modifier.background(UnSelectTabColor)) {
                SimilarGameSection(games = it, navController)
                Spacer(modifier = Modifier.size(BaseAppDimen))
            }
        }
    }
}
