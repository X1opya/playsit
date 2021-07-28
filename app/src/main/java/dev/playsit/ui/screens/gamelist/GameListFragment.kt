package dev.playsit.ui.screens.gamelist

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import dev.playsit.core.navigations.navigateToGameScreen
import dev.playsit.core.utils.toReadableDate
import dev.playsit.dto.FeedItem
import dev.playsit.ui.components.gameCards.GenreCardList
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.imageContainers.ImageType
import dev.playsit.ui.components.lists.ListWithTitle
import dev.playsit.ui.components.text.DefaultGrayText
import dev.playsit.ui.components.text.GameTitle
import dev.playsit.ui.screens.feed.compilations.CompilationProvider
import dev.playsit.ui.screens.feed.compilations.utils.Title
import dev.playsit.ui.screens.game.GamePlatformIcons

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameListFragment(
    lazyGameProvider: CompilationProvider,
    navController: NavHostController,
    hasHeader: Boolean
) {
    val items = lazyGameProvider.lazyFeedItems.collectAsLazyPagingItems()
    val subTitle = lazyGameProvider.getSubTitle()
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
            Log.d(
                "TEST_HEADERS",
                "date ${current?.firstReleaseDate.toReadableDate()} hasHeader $hasHeader"
            )
            if (current?.firstReleaseDate.toReadableDate() !=
                lastGame?.firstReleaseDate?.toReadableDate() && hasHeader
            ) {
                stickyHeader {
                    GameStickyHeader(current?.firstReleaseDate.toReadableDate())
                }
            } else if (index != 0) stickyHeader {
                Spacer(modifier = Modifier.size(25.dp))
            }
            item {
                val game = items.getAsState(index)
                game.value?.let {
                    Row(Modifier.clickable {
                        navController.navigateToGameScreen(it)
                    }) {
                        GameImageCard(uri = it.cover, imageType = ImageType.Mini, rating = null)
                        Spacer(modifier = Modifier.size(20.dp))
                        Column(modifier = Modifier.fillMaxWidth()) {
                            GameTitle(it.name)
                            Spacer(modifier = Modifier.size(5.dp))
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

enum class TITLE_TYPE {
    RELEASE,

}

@Composable
fun GameStickyHeader(title: String) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.Black
            )
            .padding(vertical = 15.dp)
    ) {
        DefaultGrayText(text = title)
    }
}
