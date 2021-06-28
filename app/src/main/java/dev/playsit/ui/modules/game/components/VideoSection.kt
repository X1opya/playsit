package dev.playsit.ui.modules.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.playsit.R
import dev.playsit.dto.GameVideo
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.modules.feed.compilations.video.VideoImageCard
import dev.playsit.ui.modules.ytplayer.YouTubeActivity
import dev.playsit.ui.theme.BaseAppDimen

@Composable
fun VideSectionList(videos: List<GameVideo>) {
    val context = LocalContext.current
    Spacer(modifier = Modifier.size(40.dp))
    CategoryTitleText(
        text = stringResource(id = R.string.videos),
        modifier = Modifier.padding(horizontal = BaseAppDimen)
    )
    Spacer(modifier = Modifier.size(BaseAppDimen))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BaseAppDimen),
        state = rememberLazyListState()
    ) {
        itemsIndexed(videos) { index, value ->
            if (index == 0) Spacer(modifier = Modifier.padding(start = BaseAppDimen))
            VideoImageCard(value) {
                it?.let { it1 -> YouTubeActivity.navigateToPlayer(context, it1) }
            }
        }
    }
}
