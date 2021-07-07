package dev.playsit.ui.components.gameCards

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.playsit.dto.Dlc
import dev.playsit.dto.GameCardDto
import dev.playsit.ui.components.effects.placeholder
import dev.playsit.ui.components.imageContainers.GameImageCard
import dev.playsit.ui.components.imageContainers.ImageType
import dev.playsit.ui.components.text.GameTitle
import dev.playsit.ui.components.text.GenreText
import dev.playsit.ui.theme.PlaceHolderDefaultLoading
import dev.playsit.ui.theme.UnSelectTabColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameCard(
    feedItem: GameCardDto?,
    cardType: ImageType,
    hasGenre: Boolean,
    loading: Boolean = PlaceHolderDefaultLoading,
    onClick: () -> Unit
) {
    Log.d("TEST_CARD", "GameCard")
    ConstraintLayout(Modifier.clickable { onClick() }) {
        val (image, title, genreList) = createRefs()
        GameImageCard(
            uri = feedItem?.cover,
            rating = null,
            imageType = cardType,
            Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            loading = loading,
        )
        GameTitle(
            text = feedItem?.name ?: "",
            modifier = Modifier
                .placeholder(loading, 225.dp, 24.dp)
                .constrainAs(title) {
                    end.linkTo(image.end)
                    start.linkTo(image.start)
                    top.linkTo(image.bottom, margin = 15.dp)
                    width = Dimension.fillToConstraints
                    centerHorizontallyTo(parent)
                })
        feedItem.takeIf { hasGenre }?.genres?.let {
            GenreCardList(it,
                Modifier
                    .placeholder(loading, 70.dp, 34.dp)
                    .constrainAs(genreList) {
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
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .then(modifier)
    ) {
        genres.forEach { genre ->
            GenreCard(genre = genre)
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun GenreCard(genre: String, iconId: Int? = null) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(UnSelectTabColor)
            .height(34.dp)
            .padding(horizontal = 15.dp),
        backgroundColor = UnSelectTabColor
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            iconId?.let {
                Icon(painterResource(it), contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.size(7.dp))
            }
            GenreText(text = genre)
        }
    }
}
