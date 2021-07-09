package dev.playsit.ui.components.lists

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import dev.playsit.R
import dev.playsit.dto.FeedItem
import dev.playsit.ui.modules.feed.compilations.CategoryTitle
import dev.playsit.ui.modules.feed.compilations.utils.Title
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
                Spacer(modifier = Modifier.size(21.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painterResource(R.drawable.ic_arrow_left),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.clickable { navController.popBackStack() }
                    )

                    Icon(
                        painterResource(R.drawable.ic_share),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.size(21.dp))
                CategoryTitle(title, hideExpandButton = true)
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(DividerColor)
                )
            }) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(BaseAppDimen),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Spacer(modifier = Modifier.size(21.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_arrow_left),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.clickable { navController.popBackStack() }
                        )

                        Icon(
                            painterResource(R.drawable.ic_share),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.size(21.dp))
                    CategoryTitle(title, hideExpandButton = true)
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(DividerColor)
                    )
                }
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
