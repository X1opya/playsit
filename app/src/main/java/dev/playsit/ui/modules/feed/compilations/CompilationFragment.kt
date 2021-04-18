package dev.playsit.ui.modules.feed.compilations

import android.content.Intent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import dev.playsit.R
import dev.playsit.ui.components.CustomAppBar
import dev.playsit.ui.modules.feed.DiscoverViewModel
import dev.playsit.ui.modules.feed.FeedPager
import dev.playsit.ui.modules.ytplayer.YouTubeActivity

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun CompilationScreen(
    discoverViewModel: DiscoverViewModel,
    compilations: State<MutableList<CompilationProvider>?>,
    navController: NavHostController
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CustomAppBar(title = stringResource(id = R.string.discoverTitle))
        AnimatedVisibility(visible = !discoverViewModel._isLoading.collectAsState().value,
            enter = slideInVertically(
                initialOffsetY = { 400 }
            ) + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()) {
            FeedPager() {
                Spacer(modifier = Modifier.padding(bottom = 25.dp))
                Column(Modifier.fillMaxHeight()) {
                    compilations.value?.forEachIndexed { index, list ->
                        if (list.type == "games") {
                            CompilationList(list, index == 0) {
                                navController.navigate("GameDetail/$it")
                            }
                        } else if (list.type == "videos") {
                            VideCompilationList(list) { videoId ->
                                val intent = Intent(context, YouTubeActivity::class.java)
                                intent.putExtras(bundleOf(YouTubeActivity.VIDEO_ID to videoId))
                                context.startActivity(intent)
                            }
                        }
                        Spacer(modifier = Modifier.padding(bottom = 40.dp))
                    }
                }
            }
        }
    }
}