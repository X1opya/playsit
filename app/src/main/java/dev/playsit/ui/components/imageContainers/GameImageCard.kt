package dev.playsit.ui.components.imageContainers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage

@Composable
fun GameImageCard(
    uri: String?,
    rating: Float?,
    imageType: ImageType = ImageType.Small,
    modifier: Modifier = Modifier
) {
    val width = getImageWidth(imageType)
    val height = getImageHeight(imageType)
    uri ?: return
    Box(modifier = Modifier.then(modifier)
        .size(width, height)) {
        CoilImage(
            data = uri,
            modifier = Modifier
                .size(width, height)
                .clip(RoundedCornerShape(16.dp)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            fadeIn = true
        )
        rating?.let {
            Box(modifier = Modifier.padding(10.dp)) {
                RatingCard(rating = rating)
            }
        }
    }
}

private fun getImageWidth(imageType: ImageType) =
    if (imageType == ImageType.Big) 225.dp else 143.dp

private fun getImageHeight(imageType: ImageType) =
    if (imageType == ImageType.Big) 300.dp else 190.dp

enum class ImageType {
    Big,
    Small
}