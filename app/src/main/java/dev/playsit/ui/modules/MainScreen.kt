package dev.playsit.ui.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.playsit.R
import dev.playsit.model.FeedItem
import dev.playsit.ui.components.AppBarr
import dev.playsit.ui.components.gameCards.PopularCard
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.modules.feed.FeedPager

//private val feedItem = remember {
//    FeedItem(
//        id = 1,
//        name = "The Witcher 3: Wild Hunt",
//        slug = "The Witcher 3: Wild Hunt",
//        firstReleaseDate = "",
//        cover = "https://image.api.playstation.com/vulcan/img/rnd/202009/2913/TQKAd8U6hnIFQIIcz6qnFh8C.png",
//        genres = listOf("Action", "RPG"),
//        platforms = listOf("PC"),
//        publisher = listOf("CD PROJEKT RED"),
//        developer = listOf("CD PROJEKT RED"),
//        involvedCompanies = null,
//        addedDate = "",
//        _duration = null,
//        videoIdentifier = null,
//        gameId = null,
//        videoType = null
//    )
//}

@Composable
fun MainScreen() {
    val feedItem = remember(1) {
        FeedItem(
            id = 1,
            name = "The Witcher 3: Wild Hunt",
            slug = "The Witcher 3: Wild Hunt",
            firstReleaseDate = "",
            cover = "https://image.api.playstation.com/vulcan/img/rnd/202009/2913/TQKAd8U6hnIFQIIcz6qnFh8C.png",
            genres = listOf("Action", "RPG","Action", "RPG","Action", "RPG","Action", "RPG"),
            platforms = listOf("PC"),
            publisher = listOf("CD PROJEKT RED"),
            developer = listOf("CD PROJEKT RED"),
            involvedCompanies = null,
            addedDate = "",
            _duration = null,
            videoIdentifier = null,
            gameId = null,
            videoType = null
        )
    }
    Scaffold(
        topBar = {
            AppBarr(title = stringResource(id = R.string.discoverTitle))
        },
        modifier = Modifier.fillMaxSize()
    ) {
//        val pages = createPages()
        FeedPager() {
//            GameImageCard("https://www.meme-arsenal.com/memes/e9a1b4bbfb8104ba30ac77636afc2973.jpg",3f)
            PopularCard(feedItem = feedItem)
        }
    }
}
