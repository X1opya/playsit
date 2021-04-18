package dev.playsit.ui.modules.feed.game

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.text.CategoryTitle

@Composable
fun GameDetail(id: Int) {
    Log.d("TEST_NAVIGATION", "GameDetail inside")
    val gameViewModel: GameViewModel = hiltNavGraphViewModel()
//    launchScreen(gameViewModel)
}

@Composable
fun launchScreen(gameViewModel: GameViewModel) {
    Log.d("TEST_NAVIGATION", "GameDetail inside2")
//    val game = gameViewModel.game.observeAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(top = 224.dp))
//        GameImageCard(uri = game.value?.cover, rating = game.value?.ratingCount?.toFloat())
        Spacer(modifier = Modifier.padding(top = 25.dp))
//        CategoryTitle(text = game.value?.name ?: "")
        Spacer(modifier = Modifier.padding(top = 5.dp))

    }
}
