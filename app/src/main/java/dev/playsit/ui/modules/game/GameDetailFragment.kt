package dev.playsit.ui.modules.feed.game

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import dev.playsit.model.Game
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.text.CategoryTitleText

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
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(top = 224.dp))
        GameImageCard(uri = game?.cover, rating = game?.ratingCount?.toFloat())
        Spacer(modifier = Modifier.padding(top = 25.dp))
        CategoryTitleText(text = game?.name ?: "")
        Spacer(modifier = Modifier.padding(top = 5.dp))
    }
}
