package dev.playsit.ui.components.pager

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import com.google.accompanist.pager.*
import dev.playsit.R
import dev.playsit.ui.theme.*
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalDensity

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedPager(
    content: @Composable (page: Int, modifier: Modifier) -> Unit,
    secondContent: @Composable (page: Int, modifier: Modifier) -> Unit
) {
    val pages = listOf(
        stringResource(R.string.collectionsTitle),
        stringResource(R.string.boardsTitle)
    )
    Column {
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
        var contentHeight: MutableState<Dp?> = remember {
            mutableStateOf(null)
        }
        var currentHeight: MutableState<Dp?> = remember {
            mutableStateOf(null)
        }
        val count = remember {
            mutableStateOf(0)
        }

        var content1Height: MutableState<Dp?> = remember {
            mutableStateOf(null)
        }
        var current1Height: MutableState<Dp?> = remember {
            mutableStateOf(null)
        }
        val count1 = remember {
            mutableStateOf(0)
        }

        val density = LocalDensity.current.density
//        contentHeight.value = with(LocalDensity.current) {1333.toDp() }
        HorizontalPager(
            state = pagerState,
            Modifier
                .then(
                    if (count.value == 2 && contentHeight.value != null) {
                        Log.d("TEST_SIZE", "HorizontalPager: then ${contentHeight.value}")
                        Modifier.height(contentHeight.value!!)
                    } else {
                        Modifier
                    }
                ),
            dragEnabled = false,
            verticalAlignment = Alignment.Top
        ) { index ->
            Box {
                if (index == 0) {
                    content(index, Modifier.onGloballyPositioned { cord ->
//                            if(contentHeight.value == null) {
//                                with(LocalDensity.current) {16.toDp}
                        val height = (cord.size.height.toFloat() / density).dp
                        if (count.value == 2 && contentHeight.value == null)
                            contentHeight.value = height
                        else if (count.value < 3 && currentHeight.value != height) {
                            count.value++
                            currentHeight.value = height
                        }

                        Log.d(
                            "TEST_SIZE_2",
                            "FeedPager: size ${cord.size} count ${count.value} \n contentHeight ${contentHeight.value} currentHeight ${currentHeight.value}"
                        )
//                        if (count.value < 1 )
//                            count.value++
                    })
                } else if (index == 1) {
                    secondContent(index, Modifier.onGloballyPositioned { cord ->
//                            if(contentHeight.value == null) {
//                                with(LocalDensity.current) {16.toDp}
                        val height = (cord.size.height.toFloat() / density).dp
                        if (count1.value == 2 && content1Height.value == null)
                            content1Height.value = height
                        else if (count.value < 3 && current1Height.value != height) {
                            count1.value++
                            current1Height.value = height
                        }

                        Log.d(
                            "TEST_SIZE",
                            "FeedPager: size ${cord.size} count ${count.value} \n contentHeight ${contentHeight.value} currentHeight ${currentHeight.value}"
                        )
//                        if (count.value < 1 )
//                            count.value++
                    })
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