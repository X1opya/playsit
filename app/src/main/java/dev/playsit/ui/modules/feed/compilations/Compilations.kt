package dev.playsit.ui.modules.feed.compilations

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import dev.playsit.ui.components.gameCards.BigGameCard
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.components.text.DescriptionText
import dev.playsit.ui.theme.BaseAppDimen
import dev.playsit.R
import dev.playsit.dto.FeedItem
import dev.playsit.ui.components.effects.placeholder
import dev.playsit.ui.components.gameCards.GameCard
import dev.playsit.ui.components.imageContainers.ImageType
import dev.playsit.ui.components.imageContainers.getGameImageHeight
import dev.playsit.ui.components.imageContainers.getGameImageWidth
import dev.playsit.ui.modules.feed.compilations.utils.Title
import dev.playsit.ui.modules.feed.compilations.video.VideoImageCard
import dev.playsit.ui.modules.ytplayer.YouTubeActivity
import dev.playsit.ui.theme.PlaceHolderDefaultLoading

typealias OnGameSelect = (feedItem: FeedItem) -> Unit

@Composable
fun CompilationList(
    compilation: CompilationProvider?,
    isMain: Boolean,
    load: Boolean = PlaceHolderDefaultLoading,
    onCategoryClick: (() -> Unit)? = null,
    onItemClick: OnGameSelect
) {
    val lazyItems = compilation?.lazyFeedItems?.collectAsLazyPagingItems()
    val refreshing = lazyItems?.loadState?.refresh == LoadState.Loading
    val appendLoading = lazyItems?.loadState?.append == LoadState.Loading
    val loading =
        refreshing || compilation?.shitCatch == false || load
    if (!load && !refreshing) compilation?.shitCatch = true
    compilation?.loading = loading
    val imageType = if (isMain) ImageType.Big else ImageType.Small
    Column {
        if (!loading && lazyItems != null) {
            CategoryTitle(
                compilation,
                onCategoryClick = onCategoryClick,
                modifier = Modifier
                    .padding(start = BaseAppDimen)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(BaseAppDimen),
                state = rememberLazyListState()
            ) {
                itemsIndexed(lazyItems) { index, value ->
                    if (value != null) {
                        if (index == 0) Spacer(modifier = Modifier.padding(start = BaseAppDimen))
                        GameCard(
                            value,
                            imageType,
                            onClick = { onItemClick.invoke(value) },
                            hasGenre = isMain
                        )
                    }
                }
                if (appendLoading) {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(
                                getGameImageWidth(imageType),
                                getGameImageHeight(imageType)
                            )
                        ) {
                            CircularProgressIndicator(color = Color.White)
                        }
                    }
                }
            }
        } else if (loading) {
            Skeleton()
        }
    }
}

@Preview
@Composable
private fun Skeleton() {
    val loading = true
    Column(Modifier.padding(start = BaseAppDimen)) {
        Box(
            modifier = Modifier
                .placeholder(loading, 88.dp, 34.dp)
        )
        Spacer(modifier = Modifier.size(7.dp))
        Box(
            modifier = Modifier
                .placeholder(loading, 134.dp, 20.dp)
        )
        Spacer(modifier = Modifier.size(BaseAppDimen))
        Row {
            repeat(2) {
                BigGameCard(
                    null,
                    loading = loading
                ) {}
                Spacer(modifier = Modifier.size(25.dp))
            }
        }
    }
}

@Composable
fun VideCompilationList(
    compilation: CompilationProvider,
    onCategoryClick: () -> Unit
) {
    val context = LocalContext.current
    val lazyItems = compilation.lazyFeedItems.collectAsLazyPagingItems()
    val refreshing = lazyItems.loadState.refresh == LoadState.Loading
    val loading = refreshing || !compilation.shitCatch
    if (!refreshing) compilation.shitCatch = true
    if (!loading) {
        Column {
            CategoryTitle(
                compilation,
                onCategoryClick = onCategoryClick,
                modifier = Modifier.padding(start = BaseAppDimen)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(BaseAppDimen),
                state = rememberLazyListState()
            ) {
                itemsIndexed(lazyItems) { index, value ->
                    if (index == 0) Spacer(modifier = Modifier.padding(start = BaseAppDimen))
                    value?.let {
                        VideoImageCard(it, onClick = {
                            YouTubeActivity.navigateToPlayer(context, value.videoIdentifier!!)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryTitle(
    title: Title?,
    onCategoryClick: (() -> Unit)? = null,
    hideExpandButton: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        Modifier
            .then(modifier)
    ) {
        ConstraintLayout(Modifier.fillMaxWidth()) {
            val (titleView, imageView) = createRefs()
            CategoryTitleText(
                text = title?.getName() ?: "",
                modifier = Modifier.constrainAs(titleView) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            )
            if (!hideExpandButton) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_grid),
                    contentDescription = null,
                    Modifier
                        .size(16.dp)
                        .constrainAs(imageView) {
                            end.linkTo(parent.end, margin = 25.dp)
                            top.linkTo(titleView.top)
                            bottom.linkTo(titleView.bottom)
                        }
                        .clickable { onCategoryClick?.invoke() },
                    tint = Color.White,
                )
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
        DescriptionText(text = title?.getSubTitle() ?: "")
        Spacer(modifier = Modifier.padding(15.dp))
    }
}
