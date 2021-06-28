package dev.playsit.ui.modules.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.playsit.R
import dev.playsit.ui.theme.GrayTextColor
import dev.playsit.ui.theme.RATING_GREEN
import dev.playsit.ui.theme.UnSelectTabColor

@Composable
fun GameReviewAndRating(reviewCount: String?, rating: String?, onSelect: OnReviewAndRatingClick) {
    Row(Modifier.padding(horizontal = 25.dp).fillMaxWidth(), Arrangement.SpaceBetween) {
        GameRating(rating = rating,
            Modifier
                .clickable { onSelect.onRatingClick() }
                .weight(1f, false))
        GameReview(reviewCount,
            Modifier
                .clickable { onSelect.onReviewClick() }
                .weight(1f, false))
    }
}

@Composable
fun GameReview(count: String?, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(145.dp, 80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(UnSelectTabColor)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = count ?: "0", color = Color.White, fontSize = 20.sp)
            Text(
                text = stringResource(id = R.string.reviews),
                color = GrayTextColor,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun GameRating(rating: String?, modifier: Modifier = Modifier) {
    Box(
        Modifier
            .size(145.dp, 80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (!rating.isNullOrZero()) Color(0x330fc545) else UnSelectTabColor)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        if (!rating.isNullOrZero()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_laurels),
                contentDescription = null,
                tint = RATING_GREEN
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                GreenText(text = rating.toString(), fontSize = 20.sp)
                GreenText(text = stringResource(id = R.string.rating), fontSize = 14.sp)
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "n/a", fontSize = 20.sp, color = Color.White)
                Text(
                    text = stringResource(id = R.string.rating),
                    fontSize = 14.sp,
                    color = GrayTextColor
                )
            }
        }
    }
}

@Composable
fun GreenText(text: String, fontSize: TextUnit) {
    Text(text = text, fontSize = fontSize, color = RATING_GREEN)
}

@Composable
@Preview
fun PreviewGameRating() {
    Column() {
        val click = object : OnReviewAndRatingClick {
            override fun onRatingClick() {}
            override fun onReviewClick() {}
        }
        GameReviewAndRating("123", "123", click)
    }
}

interface OnReviewAndRatingClick {
    fun onRatingClick()
    fun onReviewClick()
}

private fun String?.isNullOrZero() = this == null || this == "0"
