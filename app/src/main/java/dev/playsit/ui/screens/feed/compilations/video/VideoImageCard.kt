package dev.playsit.ui.screens.feed.compilations.video

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.coil.rememberCoilPainter
import dev.playsit.R
import dev.playsit.dto.VideoDto
import dev.playsit.ui.components.text.DescriptionText
import dev.playsit.ui.components.text.GameTitle
import dev.playsit.ui.theme.UnSelectTabColor

@Composable
fun VideoImageCard(videoItem: VideoDto, modifier: Modifier = Modifier.size(225.dp, 127.dp), onClick: (String?) -> Unit) {
    ConstraintLayout(modifier = Modifier.clickable { onClick(videoItem.videoIdentifier) }) {
        val (image, icon, title, desc, duration) = createRefs()
        Image(
            painter = rememberCoilPainter(request = videoItem.cover ?: "", fadeIn = true),
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .then(modifier)
                .clip(RoundedCornerShape(16.dp)),

            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_play),
            tint = Color.Unspecified,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(image.top)
                    bottom.linkTo(image.bottom)
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                }
        )
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(UnSelectTabColor)
            .wrapContentSize(Alignment.Center)
            .padding(6.dp)
            .constrainAs(duration) {
                bottom.linkTo(image.bottom, 8.dp)
                end.linkTo(image.end, 8.dp)
            }) {
            Text(
                text = videoItem.duration,
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.White
            )
        }

        GameTitle(
            text = videoItem.name,
            modifier = Modifier.constrainAs(title) {
                end.linkTo(image.end)
                start.linkTo(image.start)
                top.linkTo(image.bottom, margin = 15.dp)
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            })
        DescriptionText(text = videoItem.formattedAddedDate, Modifier.constrainAs(desc) {
            end.linkTo(title.end)
            start.linkTo(title.start)
            top.linkTo(title.bottom, margin = 5.dp)
            width = Dimension.fillToConstraints
            centerHorizontallyTo(parent)
        })
    }
}

@Composable
@Preview
fun VideoImageCardPreview() {
    VideoImageCard(object : VideoDto {
        override val duration: String
            get() = "123"
        override val videoIdentifier: String?
            get() = "videoIdentifier"
        override val cover: String?
            get() = "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
        override val formattedAddedDate: String
            get() = "213"
        override val name: String
            get() = "name"
    }) {}
}
