package dev.playsit.ui.modules.gamelist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.playsit.R
import dev.playsit.core.utils.toReadableDate
import dev.playsit.dto.FeedItem
import dev.playsit.ui.components.gameCards.GenreCardList
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.imageContainers.ImageType
import dev.playsit.ui.components.lists.ListWithTitle
import dev.playsit.ui.components.text.DefaultGrayText
import dev.playsit.ui.components.text.GameTitle
import dev.playsit.ui.modules.feed.compilations.CompilationProvider
import dev.playsit.ui.modules.feed.compilations.utils.Title
import dev.playsit.ui.modules.game.GamePlatformIcons

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameListFragment(
    lazyGameProvider: CompilationProvider,
    navController: NavHostController,
    hasHeader: Boolean
) {
    val items = lazyGameProvider.lazyFeedItems.collectAsLazyPagingItems()
    val subTitle = stringResource(id = R.string.last7days)
    var lastGame: FeedItem? = null
    ListWithTitle(
        title = object : Title {
            override fun getName() = lazyGameProvider.getName()
            override fun getSubTitle() = subTitle
        },
        lazyItems = items,
        navController = navController
    ) {
        for (index in 0 until items.itemCount) {
            val current = items.peek(index)
            if (current?.firstReleaseDate.toReadableDate() !=
                lastGame?.firstReleaseDate?.toReadableDate()
            ) {
                stickyHeader {
                    GameStickyHeader(current?.firstReleaseDate.toReadableDate())
                }
            }
            item {
                val game = items.getAsState(index)
                game.value?.let {
                    Row {
                        GameImageCard(uri = it.cover, imageType = ImageType.Mini, rating = null)
                        Spacer(modifier = Modifier.size(20.dp))
                        Column(modifier = Modifier.fillMaxWidth()) {
                            GameTitle(it.name)
                            Spacer(modifier = Modifier.size(20.dp))
                            DefaultGrayText(it.publisher?.get(0) ?: "")
                            it.uniquePlatforms.let {
                                Spacer(modifier = Modifier.size(20.dp))
                                GamePlatformIcons(it)
                            }
                            Spacer(modifier = Modifier.size(33.dp))
                            GenreCardList(
                                genres = it.genres ?: listOf(),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
            lastGame = items.peek(index)
        }
    }
}

@Composable
fun GameStickyHeader(title: String) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.Black
            ).padding(vertical = 15.dp)
    ) {
        DefaultGrayText(text = title)
    }
}
