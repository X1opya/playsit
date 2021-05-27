package dev.playsit.ui.modules

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.playsit.ui.components.pager.pagerTabIndicatorOffset
import dev.playsit.ui.modules.feed.DiscoverViewModel
import dev.playsit.ui.modules.feed.FeedFragment
import dev.playsit.ui.modules.feed.game.GameDetail
import dev.playsit.ui.theme.BaseAppDimen
import dev.playsit.ui.theme.DividerColor
import dev.playsit.ui.theme.SelectTabColor
import dev.playsit.ui.theme.UnSelectTabColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(discoverViewModel: DiscoverViewModel, navController: NavHostController) {
    val compilations =
        discoverViewModel.compilations.observeAsState()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
//            FeedFragment(discoverViewModel, compilations, navController)
            PagerSample()
        }

        composable(
            "GameDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }
        )) { backStack ->
            backStack.arguments?.getInt("id")?.let { GameDetail(id = it) }
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerSample() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        val pages = listOf(
            "page one",
            "page two"
        )
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = 2)
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 0.dp,
                    color = Color.Transparent
                )
            },
            modifier = Modifier
                .padding(
                    bottom = BaseAppDimen,
                    start = BaseAppDimen,
                    end = BaseAppDimen,
                    top = 0.dp
                )
                .clip(RoundedCornerShape(10.dp)),
            backgroundColor = UnSelectTabColor

        ) {
            pages.forEachIndexed { index, title ->
                val isSelected = pagerState.currentPage == index
                Tab(
                    text = {
                        Text(text = title)
                    },
                    selected = isSelected,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    selectedContentColor = SelectTabColor,
                    unselectedContentColor = UnSelectTabColor
                )
            }
        }

        Divider(color = DividerColor)

        HorizontalPager(
            state = pagerState,
            Modifier.wrapContentHeight(),
            dragEnabled = false,
            verticalAlignment = Alignment.Top
        ) { index ->
                if (index == 0) {
                    Column(Modifier) {
                        repeat(10) {
                            Text(text = "page 1", Modifier.size(100.dp))
                        }
                    }
                } else if (index == 1) {
                    Column() {
                        repeat(20) {
                            Text(text = "page 2", Modifier.size(100.dp))
                        }
                    }

            }
        }
    }
}