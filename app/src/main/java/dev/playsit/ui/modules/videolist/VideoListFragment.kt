package dev.playsit.ui.modules.videolist

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import dev.playsit.R
import dev.playsit.ui.components.effects.placeholder
import dev.playsit.ui.components.lists.ListWithTitle
import dev.playsit.ui.modules.feed.compilations.CompilationProvider
import dev.playsit.ui.modules.feed.compilations.utils.Title
import dev.playsit.ui.modules.feed.compilations.video.VideoImageCard
import dev.playsit.ui.modules.ytplayer.YouTubeActivity
import dev.playsit.ui.theme.BaseAppDimen

@Composable
fun VideoListFragment(lazyVideoProvider: CompilationProvider, navController: NavHostController) {
    val context = LocalContext.current
    val lazyItems = lazyVideoProvider.lazyFeedItems.collectAsLazyPagingItems()
    val title = stringResource(id = R.string.videos)
    val subTitle = stringResource(id = R.string.last7days)
    ListWithTitle(object : Title {
        override fun getName() = title
        override fun getSubTitle() = subTitle
    }, lazyItems, navController) {
        itemsIndexed(lazyItems) { index, value ->
            value?.let {
                VideoImageCard(
                    value, onClick = {
                        YouTubeActivity.navigateToPlayer(
                            context,
                            value.videoIdentifier!!
                        )
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .height(174.dp)
                )
                Spacer(modifier = Modifier.size(25.dp))
            }
        }
    }
}

@Composable
@Preview
fun VideoListSkeleton() {
    VideoItemSkeleton()
    Spacer(modifier = Modifier.size(BaseAppDimen))
    VideoItemSkeleton()
    Spacer(modifier = Modifier.size(BaseAppDimen))
    VideoItemSkeleton()
    Spacer(modifier = Modifier.size(BaseAppDimen))
    VideoItemSkeleton()
}

@Composable
@Preview
fun VideoItemSkeleton() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(174.dp)
                .placeholder(true)
        )
        Spacer(modifier = Modifier.size(15.dp))

        Box(
            modifier = Modifier
                .placeholder(true, 225.dp, 24.dp)
        )
        Spacer(modifier = Modifier.size(7.dp))
        Box(
            modifier = Modifier
                .placeholder(true, 100.dp, 20.dp)
        )
    }
}
