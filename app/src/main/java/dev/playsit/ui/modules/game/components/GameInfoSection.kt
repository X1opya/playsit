package dev.playsit.ui.modules.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.playsit.R
import dev.playsit.core.utils.toReadableDate
import dev.playsit.dto.Game
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.components.text.DefaultGrayText
import dev.playsit.ui.components.text.DefaultWhiteText
import dev.playsit.dto.*
import dev.playsit.ui.components.gameCards.GenreCard
import dev.playsit.ui.theme.UnSelectTabColor

@Composable
fun GameInfoSection(game: Game) {
    Column {
        Column(Modifier.padding(horizontal = 25.dp)) {
            CategoryTitleText(text = stringResource(id = R.string.info))
            Spacer(modifier = Modifier.size(25.dp))
            DefaultWhiteText(text = game.summary)
            Spacer(modifier = Modifier.size(25.dp))
            Row {
                DefaultGrayText(
                    text = stringResource(id = R.string.releaseDate),
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                DefaultGrayText(
                    text = stringResource(id = R.string.ageRating),
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            Row {
                DefaultWhiteText(
                    text = game.firstReleaseDate.toReadableDate(),
                    modifier = Modifier.weight(1f)
                )
                DefaultWhiteText(text = game.ageRating ?: "", modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.size(25.dp))

            Row {
                DefaultGrayText(
                    text = stringResource(id = R.string.willPlay),
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                DefaultGrayText(
                    text = stringResource(id = R.string.played),
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            Row {
                DefaultWhiteText(
                    text = game.willPlayCount.toString(),
                    modifier = Modifier.weight(1f)
                )
                DefaultWhiteText(text = game.playedCount.toString(), modifier = Modifier.weight(1f))
            }
        }
        GameCommonPlanks(genres = game.genres)
        Spacer(modifier = Modifier.size(25.dp))
        GameInfoList(
            stringResource(id = R.string.platforms),
            game.platforms,
            GameImageConverter.getPlatformIdList(game.uniquePlatforms)
        )
        GameInfoList(stringResource(id = R.string.gameModes), game.gameModes)
        Spacer(modifier = Modifier.size(25.dp))
        GameInfoList(stringResource(id = R.string.developers), game.developer)
        Spacer(modifier = Modifier.size(25.dp))
        GameInfoList(stringResource(id = R.string.publishers), game.publisher)
    }
}

@Composable
fun GameCommonPlanks(genres: List<String>?) {
    if (!genres.isNullOrEmpty()) {
        val genresId = GameImageConverter.getGenreIdList(genres)
        Spacer(modifier = Modifier.size(25.dp))
        DefaultGrayText(
            text = stringResource(id = R.string.genres),
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 25.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))
        LazyRow {
            itemsIndexed(genresId) { index, item ->
                if (index == 0) Spacer(modifier = Modifier.size(25.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(UnSelectTabColor)
                    ) {
                        Icon(
                            painterResource(id = genresId[index]),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = genres[index],
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.size(25.dp))
            }
        }
    }
}

@Composable
fun GameInfoList(title: String, items: List<String>, icons: List<Int>? = null) {
    DefaultGrayText(
        text = title,
        fontSize = 16.sp,
        modifier = Modifier.padding(horizontal = 25.dp)
    )
    Spacer(modifier = Modifier.size(5.dp))
    LazyRow {
        itemsIndexed(items) { index, item ->
            if (index == 0) Spacer(modifier = Modifier.size(25.dp))
            GenreCard(genre = item, iconId = icons?.getOrNull(index))
            Spacer(modifier = Modifier.size(5.dp))
        }
    }
}
