package dev.playsit.ui.modules.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import dev.playsit.R
import dev.playsit.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedPager(content: @Composable (page: Int) -> Unit) {
    val pages = listOf(
        stringResource(R.string.collectionsTitle),
        stringResource(R.string.boardsTitle)
    )
    Column(
//        Modifier
//            .fillMaxSize()
    ) {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = pages.size)
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
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            color = getTabTextColor(isSelected)
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .size(153.dp, 48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(getTabBackgroundColor(isSelected))
                        .weight(1f),
                    selectedContentColor = SelectTabColor,
                    unselectedContentColor = UnSelectTabColor
                )
            }
        }

        Divider(color = DividerColor)

        HorizontalPager(
            state = pagerState,
            Modifier
                .fillMaxWidth(),
            dragEnabled = false
//            reverseLayout = true
        ) { index ->
            Box {
                if (index == 0) {
                    content(index)
                } else if (index == 1) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        repeat(30) {
                            Text(
                                text = "Page: page",
                                style = MaterialTheme.typography.h4,
                                modifier = Modifier,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
fun Modifier.pagerTabIndicatorOffset(
    pagerState: PagerState,
    tabPositions: List<TabPosition>,
): Modifier = composed {
    val targetIndicatorOffset: Dp
    val indicatorWidth: Dp

    val currentTab = tabPositions[pagerState.currentPage]
    val nextTab = tabPositions.getOrNull(pagerState.currentPage + 1)
    if (nextTab != null) {
        // If we have a next tab, lerp between the size and offset
        targetIndicatorOffset = lerp(currentTab.left, nextTab.left, pagerState.currentPageOffset)
        indicatorWidth = lerp(currentTab.width, nextTab.width, pagerState.currentPageOffset)
    } else {
        // Otherwise we just use the current tab/page
        targetIndicatorOffset = currentTab.left
        indicatorWidth = currentTab.width
    }

    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = targetIndicatorOffset)
        .width(indicatorWidth)
}

private fun getTabTextColor(isSelect: Boolean) =
    if (isSelect) WhiteTextColor else UnSelectTabTextColor

private fun getTabBackgroundColor(isSelect: Boolean) =
    if (isSelect) SelectTabColor else UnSelectTabColor