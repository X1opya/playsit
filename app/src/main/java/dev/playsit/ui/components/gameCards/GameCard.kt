package dev.playsit.ui.components.gameCards

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.playsit.model.FeedItem
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.imageContainers.ImageType
import dev.playsit.ui.components.text.GameTitle
import dev.playsit.ui.components.text.GenreText
import dev.playsit.ui.theme.UnSelectTabColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameCard(feedItem: FeedItem, cardType: ImageType, hasGenre: Boolean) {
    ConstraintLayout {

        val (image, title, genreList) = createRefs()
        GameImageCard(
            uri = feedItem.cover,
            rating = null,
            imageType = cardType,
            Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        GameTitle(
            text = feedItem.name,
            modifier = Modifier.constrainAs(title) {
                end.linkTo(image.end)
                start.linkTo(image.start)
                top.linkTo(image.bottom, margin = 15.dp)
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            })
        feedItem.takeIf { hasGenre }?.genres?.let {
            GenreCardList(it, Modifier.constrainAs(genreList) {
                end.linkTo(image.end)
                start.linkTo(image.start)
                top.linkTo(title.bottom, margin = 15.dp)
                width = Dimension.fillToConstraints
            })
        }
    }
}

@Composable
fun GenreCardList(genres: List<String>, modifier: Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .then(modifier)
    ) {
        items(genres) { genre ->
            GenreCard(genre = genre)
            Spacer(modifier = Modifier.padding(5.dp))
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