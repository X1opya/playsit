package dev.playsit.ui.components.gameCards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.playsit.model.FeedItem
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.text.GameTitle
import dev.playsit.ui.components.text.GenreText
import dev.playsit.ui.theme.UnSelectTabColor

@Composable
fun PopularCard(feedItem: FeedItem) {
    Column {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GameImageCard(uri = feedItem.cover, rating = null)
            Box(modifier = Modifier.padding(top = 15.dp))
            GameTitle(text = feedItem.name)
            Box(modifier = Modifier.padding(top = 15.dp))
        }
        feedItem.genres?.let { GenreCardList(it) }
    }
}

@Composable
fun GenreCardList(genres: List<String>) {
    LazyRow(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth(0.5f)) {
        items(genres) { genre ->

            GenreCard(genre = genre)
            Box(modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun GenreCard(genre: String) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(UnSelectTabColor)
            .padding(8.dp)
            .padding(horizontal = 8.dp),

        backgroundColor = UnSelectTabColor
    ) {
        GenreText(text = genre)
    }
}