package dev.playsit.ui.modules

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import dev.playsit.core.navigations.Screens
import dev.playsit.dto.Compilation.Companion.SLUG_LATEST
import dev.playsit.dto.Compilation.Companion.SLUG_UPCOMING
import dev.playsit.dto.FeedItem
import dev.playsit.ui.modules.feed.DiscoverViewModel
import dev.playsit.ui.modules.feed.FeedFragment
import dev.playsit.ui.modules.game.GameDetail
import dev.playsit.ui.modules.gamelist.GameListFragment
import dev.playsit.ui.modules.videolist.VideoListFragment

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(discoverViewModel: DiscoverViewModel, navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        val compilations =
            discoverViewModel.compilations.observeAsState()
        NavHost(navController = navController, startDestination = "mainScreen") {
            composable("mainScreen") {
                FeedFragment(discoverViewModel, compilations, navController)
            }
            composable(
                "GameDetail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType }
                )) { backStack ->
                backStack.arguments?.getInt("id")?.let {
                    val feedObject =
                        (navController.previousBackStackEntry?.arguments?.getSerializable("game") as FeedItem)
                    GameDetail(feedObject, navController)
                }
            }
            composable(Screens.toVideoList()) {
                discoverViewModel.compilations.value?.find { it.type == "videos" }
                    ?.let { videoProvider ->
                        VideoListFragment(
                            lazyVideoProvider = videoProvider,
                            navController = navController
                        )
                    }
            }
            composable("GameList/{index}",
                arguments = listOf(navArgument("index") { type = NavType.IntType })
            ) {
                it.arguments?.getInt("index")?.let { index ->
                    discoverViewModel.compilations.value?.get(index)
                        ?.let { videoProvider ->
                            GameListFragment(
                                videoProvider,
                                navController = navController,
                                videoProvider.slug == SLUG_UPCOMING || videoProvider.slug == SLUG_LATEST
                            )
                        }
                }
            }
        }
    }
}
