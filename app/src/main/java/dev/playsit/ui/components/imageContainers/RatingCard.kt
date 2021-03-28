package dev.playsit.ui.components.imageContainers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.playsit.ui.theme.RatingColorHigh
import dev.playsit.ui.theme.RatingColorLow
import dev.playsit.ui.theme.RatingColorMedium

@Composable
fun RatingCard(rating: Float) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .wrapContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .background(getRatingColor(rating))
                .padding(2.dp)
                .padding(end = 5.dp)
        ) {
            Icon(
                Icons.Filled.Star,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.scale(0.5f)
            )
            Text(
                text = rating.toUInt().toString(),
                modifier = Modifier,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private fun getRatingColor(rating: Float) =
    if (rating <= 3) RatingColorLow
    else if (rating < 7 && rating > 3) RatingColorMedium
    else RatingColorHigh