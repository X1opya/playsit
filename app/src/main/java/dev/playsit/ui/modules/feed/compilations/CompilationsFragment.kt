package dev.playsit.ui.modules.feed.compilations

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.playsit.core.navigations.Screens
import dev.playsit.ui.modules.ytplayer.YouTubeActivity

@Composable
fun CompilationsFragment(
    compilations: State<MutableList<CompilationProvider>?>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loading: State<Boolean>
) {
    val context = LocalContext.current
    Column(
        Modifier
            .wrapContentHeight()
            .padding(top = 25.dp)
            .then(modifier)
    ) {
        if (loading.value) {
            CompilationList(null, true, loading.value) {}
            Spacer(modifier = Modifier.size(40.dp))
            CompilationList(null, true, loading.value) {}
        } else {
            compilations.value?.forEachIndexed { index, list ->
                if (list.type == "games") {
                    CompilationList(list, index == 0) {
                        navController.currentBackStackEntry?.arguments =
                            Bundle().apply {
                                putSerializable("game", it)
                            }
                        navController.navigate(Screens.toGameScreen(it))
                    }
                } else if (list.type == "videos") {
                    VideCompilationList(list) { videoId ->
                        videoId?.let { YouTubeActivity.navigateToPlayer(context, it) }
                    }
                }
                Spacer(modifier = Modifier.size(40.dp))
            }
        }
    }
}
