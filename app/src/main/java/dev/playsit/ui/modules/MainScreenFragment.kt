package dev.playsit.ui.modules

import androidx.compose.animation.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import dev.playsit.dto.FeedItem
import dev.playsit.ui.modules.feed.DiscoverViewModel
import dev.playsit.ui.modules.feed.FeedFragment
import dev.playsit.ui.modules.game.GameDetail

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(discoverViewModel: DiscoverViewModel, navController: NavHostController) {
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
    }
}
