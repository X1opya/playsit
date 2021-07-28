package dev.playsit.ui.components.lists

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import dev.playsit.R
import dev.playsit.dto.FeedItem
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.components.text.DescriptionText
import dev.playsit.ui.screens.feed.compilations.utils.Title
import dev.playsit.ui.theme.BaseAppDimen
import dev.playsit.ui.theme.DividerColor
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListWithTitle(
    title: Title,
    lazyItems: LazyPagingItems<FeedItem>,
    navController: NavController,
    content: LazyListScope.() -> Unit
) {
    val append = lazyItems.loadState.append == LoadState.Loading
    Column(modifier = Modifier.padding(horizontal = BaseAppDimen)) {
        val state = rememberCollapsingToolbarScaffoldState()
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                val titleSize = (16 + (30 - 16) * state.toolbarState.progress).sp
                val titlePadding = (14 + 40 * state.toolbarState.progress).dp

                val subTitlePadding = (25 * state.toolbarState.progress).dp
                val deviderPadding = (60 + (156-60) * state.toolbarState.progress).dp
                Log.d(
                    "TEST_SCROLL",
                    "scroll ${state.toolbarState.progress} titlePAdding = ${deviderPadding}"
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(156.dp)
                        .pin()
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 21.dp, bottom = 21.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.ic_arrow_left),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(17.dp).clickable { navController.popBackStack() }
                    )

                    Icon(
                        painterResource(R.drawable.ic_share),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(17.dp).clickable {  }
                    )
                }

                Column(modifier =
                Modifier
                    .road(Alignment.Center, Alignment.BottomStart)
                    .padding(top = 54.dp)) {

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp).background(DividerColor))
                }
                CategoryTitleText(
                    text = title.getName(),
                    modifier = Modifier
                        .road(Alignment.Center, Alignment.BottomStart)
                        .padding(bottom = titlePadding, top = 5.dp),
                    fontSize = titleSize
                )

                DescriptionText(
                    text = title.getSubTitle(), modifier = Modifier
                        .road(Alignment.Center, Alignment.BottomStart)
                        .padding(top = 27.dp, bottom = subTitlePadding),
                    fontSize = 14.sp
                )
            }) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ) {
                content()

                if (append) {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(174.dp)
                        ) {
                            CircularProgressIndicator(color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
