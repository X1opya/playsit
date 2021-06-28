package dev.playsit.ui.modules.feed.compilations

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import dev.playsit.core.navigations.Screens
import dev.playsit.ui.modules.ytplayer.YouTubeActivity

@Composable
fun CompilationsFragment(
    compilations: State<MutableList<CompilationProvider>?>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        Modifier
            .wrapContentHeight()
            .padding(top = 25.dp)
            .then(modifier)
    ) {
        compilations.value?.forEachIndexed { index, list ->
            if (list.type == "games") {
                CompilationList(list, index == 0) {
                    navController.navigate(Screens.toGameScreen(it))
                }
            } else if (list.type == "videos") {
                VideCompilationList(list) { videoId ->
                    videoId?.let { YouTubeActivity.navigateToPlayer(context, it) }
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 40.dp))
        }
    }
}
