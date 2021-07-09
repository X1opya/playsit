package dev.playsit.ui.components.effects

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import dev.playsit.ui.theme.UnSelectTabColor

fun Modifier.placeholder(visible: Boolean, width: Dp? = null, height: Dp? = null): Modifier =
    composed {
        this
            .placeholder(
                visible = visible,
                color = UnSelectTabColor,
                highlight = PlaceholderHighlight.shimmer(
                    Color.Gray,
                    infiniteRepeatable(
                        animation = tween(durationMillis = 1000, delayMillis = 100),
                        repeatMode = RepeatMode.Restart
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .then(Modifier.then(width?.let { width(it) } ?: Modifier))
            .then(Modifier.then(height?.let { height(it) } ?: Modifier))
    }
