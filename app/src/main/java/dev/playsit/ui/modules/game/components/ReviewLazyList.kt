package dev.playsit.ui.modules.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import dev.playsit.dto.Review
import dev.playsit.dto.ReviewCollections
import dev.playsit.ui.components.imageContainers.RatingCard
import dev.playsit.ui.theme.UnSelectTabColor

@Composable
fun ReviewLazyList(reviews: ReviewCollections) {
    if (!reviews.reviews.isNullOrEmpty()) {
        Spacer(modifier = Modifier.size(20.dp))
        LazyRow {
            itemsIndexed(reviews.reviews) { index, item ->
                if(index == 0) Spacer(modifier = Modifier.size(25.dp))
                ReviewItem(review = item)
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(UnSelectTabColor),
            contentAlignment = Alignment.Center
        ) {
            review.user.profileImage?.let {
                Image(
                    painter = rememberCoilPainter(review.user.profileImage, fadeIn = true),
                    contentScale = ContentScale.Crop,
                    contentDescription = null, modifier = Modifier.fillMaxSize()
                )
            } ?: Text(
                text = "AD",
                color = Color(0xff535353),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        RatingCard(rating = review.reviewScore.toString())
    }
}
