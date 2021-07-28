package dev.playsit.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navArgument
import dev.playsit.core.navigations.BottomNavScreen
import dev.playsit.core.navigations.Screens
import dev.playsit.dto.Compilation.Companion.SLUG_LATEST
import dev.playsit.dto.Compilation.Companion.SLUG_UPCOMING
import dev.playsit.dto.FeedItem
import dev.playsit.ui.screens.feed.DiscoverViewModel
import dev.playsit.ui.screens.feed.FeedFragment
import dev.playsit.ui.screens.game.GameDetail
import dev.playsit.ui.screens.gamelist.GameListFragment
import dev.playsit.ui.screens.videolist.VideoListFragment
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(discoverViewModel: DiscoverViewModel, navController: NavHostController) {
    val compilations =
        discoverViewModel.compilations.observeAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        bottomBar = {
            val backstack by navController.currentBackStackEntryAsState()
            val destination = backstack?.destination

            if (isNavScreen(navController)) {
                BottomNavigation() {
                    BottomNavScreen.screens.forEach { screen ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painterResource(screen.icon),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = screen.name),
                                    fontSize = 10.sp
                                )
                            },
                            selected = destination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                    }
                }
            }
        }) { innerPadding ->
        NavHost(navController, startDestination = BottomNavScreen.Discover.route) {
            composable(BottomNavScreen.Discover.route) {
                FeedFragment(discoverViewModel, compilations, navController)
            }
            composable(BottomNavScreen.News.route) { Text(text = "News") }
            composable(BottomNavScreen.Search.route) { Text(text = "Search") }
            composable(BottomNavScreen.Notifications.route) { Text(text = "Notifications") }
            composable(BottomNavScreen.Profile.route) { Text(text = "Profile") }

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
            composable(
                "GameList/{index}",
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

@Composable
private fun isNavScreen(navController: NavHostController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    BottomNavScreen.screens.forEach {
        if (navBackStackEntry?.destination?.route == it.route)
            return true
    }
    return false
}
