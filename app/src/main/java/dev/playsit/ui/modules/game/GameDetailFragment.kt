package dev.playsit.ui.modules.game

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.playsit.model.Game
import dev.playsit.model.GameImageConverter
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.components.text.DefaultGrayText

@Composable
fun GameDetail(id: Int) {
    val gameViewModel: GameViewModel = hiltViewModel()
    val game by gameViewModel.game.observeAsState()
    DisposableEffect(key1 = id) {
        gameViewModel.getGameById(id)
        onDispose {
            gameViewModel.onDestroy()
        }
    }
    LaunchScreen(game)
}

@Composable
private fun LaunchScreen(game: Game?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        Spacer(modifier = Modifier.padding(top = 224.dp))
        GameImageCard(
            uri = game?.cover,
            rating = game?.ratingCount?.toString() ?: game?.rating?.toString()
            ?: "n/a"
        )
        Spacer(modifier = Modifier.padding(top = 25.dp))
        CategoryTitleText(text = game?.name ?: "", maxLines = 3, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(top = 5.dp))
        DefaultGrayText(text = game?.developer?.get(0) ?: "")
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row {
            val list = GameImageConverter
                .getPlatformIdList(game?.uniquePlatforms ?: emptyList())
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
            game?.reviewsCount?.toString(),
            game?.aggregatedRating?.toInt()?.toString(),
            object : OnReviewAndRatingClick {
                override fun onRatingClick() { }
                override fun onReviewClick() { }
            })
    }
}
