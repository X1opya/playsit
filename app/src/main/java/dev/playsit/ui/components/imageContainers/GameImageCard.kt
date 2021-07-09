package dev.playsit.ui.components.imageContainers

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import dev.playsit.ui.components.effects.placeholder
import dev.playsit.ui.theme.PlaceHolderDefaultLoading

@Composable
fun GameImageCard(
    uri: String?,
    rating: String?,
    imageType: ImageType = ImageType.Small,
    modifier: Modifier = Modifier,
    loading: Boolean = PlaceHolderDefaultLoading
) {
    val painter = rememberCoilPainter(request = uri, fadeIn = true)
    Log.d("TEST_CARD", "GameImageCard")
    val width = getGameImageWidth(imageType)
    val height = getGameImageHeight(imageType)
    Box(
        modifier = Modifier
            .then(modifier)
            .size(width, height)
    ) {
        Image(
            painter = painter,
            modifier = Modifier
                .size(width, height)
                .clip(RoundedCornerShape(16.dp))
                .placeholder(painter.loadState is ImageLoadState.Loading || loading),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        rating?.let {
            Box(modifier = Modifier.padding(10.dp)) {
                RatingCard(rating = rating.toString())
            }
        }
    }
}

fun getGameImageWidth(imageType: ImageType) = when(imageType) {
    ImageType.Big -> 225.dp
    ImageType.Small -> 143.dp
    ImageType.Mini -> 113.dp
}

fun getGameImageHeight(imageType: ImageType) = when(imageType) {
    ImageType.Big -> 300.dp
    ImageType.Small -> 190.dp
    ImageType.Mini -> 158.dp
}

enum class ImageType {
    Big,
    Small,
    Mini
}
