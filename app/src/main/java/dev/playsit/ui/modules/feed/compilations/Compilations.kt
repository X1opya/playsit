package dev.playsit.ui.modules.feed.compilations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import dev.playsit.ui.components.gameCards.BigGameCard
import dev.playsit.ui.components.gameCards.SmallGameCard
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.components.text.DescriptionText
import dev.playsit.ui.theme.BaseAppDimen
import dev.playsit.R
import dev.playsit.ui.modules.feed.compilations.utils.Title
import dev.playsit.ui.modules.feed.compilations.video.VideoImageCard

@Composable
fun CompilationList(compilation: CompilationProvider, isMain: Boolean, onItemClick: (id: Int) -> Unit) {
    val lazyItems = compilation.lazyFeedItems.collectAsLazyPagingItems()
    Column {
        CategoryTitle(compilation, Modifier.padding(start = BaseAppDimen))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BaseAppDimen),
            state = rememberLazyListState()
        ) {
            itemsIndexed(lazyItems) { index, value ->
                if (index == 0) Spacer(modifier = Modifier.padding(start = BaseAppDimen))
                value?.let {
                    if (isMain) BigGameCard(value, onItemClick) else SmallGameCard(
                        value,
                        onItemClick
                    )
                }
            }
        }
    }
}

@Composable
fun VideCompilationList(compilation: CompilationProvider, onItemClick: (String?) -> Unit) {
    val lazyItems = compilation.lazyFeedItems.collectAsLazyPagingItems()
    Column {

        CategoryTitle(compilation, Modifier.padding(start = BaseAppDimen))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BaseAppDimen),
            state = rememberLazyListState()
        ) {
            itemsIndexed(lazyItems) { index, value ->
                if (index == 0) Spacer(modifier = Modifier.padding(start = BaseAppDimen))
                value?.let { VideoImageCard(it, onItemClick) }
            }
        }
    }
}

@Composable
fun CategoryTitle(title: Title, modifier: Modifier = Modifier) {
    Column(
        Modifier
            .then(modifier)
    ) {
        ConstraintLayout(Modifier.fillMaxWidth()) {
            val (titleView, imageView) = createRefs()
            CategoryTitleText(
                text = title.getName(),
                modifier = Modifier.constrainAs(titleView) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_grid),
                contentDescription = null,
                Modifier
                    .size(16.dp)
                    .constrainAs(imageView) {
                        end.linkTo(parent.end, margin = 25.dp)
                        top.linkTo(titleView.top)
                        bottom.linkTo(titleView.bottom)
                    },
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        DescriptionText(text = title.getSubTitle())
        Spacer(modifier = Modifier.padding(15.dp))
    }
}
