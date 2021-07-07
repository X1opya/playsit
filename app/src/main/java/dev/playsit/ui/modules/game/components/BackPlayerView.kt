package dev.playsit.ui.modules.game.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dev.playsit.ui.theme.GRADIENT1
import dev.playsit.ui.theme.GRADIENT2

@Composable
fun BackPlayerView(videoId: String) {
    val player = rememberPlayerView()
    val density = LocalDensity.current.density
    Box() {
        var width = remember { mutableStateOf(0f) }
        val height = remember { mutableStateOf(0f) }
        AndroidView(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .scale(3f)
            .onGloballyPositioned { coordinates ->
                width.value = coordinates.size.width / density
                height.value = coordinates.size.height / density
            },
            factory = { context ->
                setupPLayer(player, videoId)
                player
            }, update = { view ->
            })
        Box(
            modifier = Modifier
                .size(width.value.dp, height.value.dp)
                .scale(3f)
                .background(
                    brush = Brush.verticalGradient(
                        0.0f to Color.Transparent,
                        0.42f to GRADIENT1,
                        0.8f to GRADIENT2,
                        1f to Color.Black,
                    )
                )
        )
    }
//    var initialized by remember(player) { mutableStateOf(false) }
}

private fun setupPLayer(player: YouTubePlayerView, videoId: String) {
    player.apply {
        var dura = 0f
        getPlayerUiController()
            .showBufferingProgress(false)
            .showFullscreenButton(false)
            .showPlayPauseButton(false)
            .showYouTubeButton(false)
            .showCurrentTime(false)
            .showMenuButton(false)
            .showVideoTitle(false)
            .showDuration(false)
            .showSeekBar(false)
            .showUi(false)
            .enableLiveVideoUi(false)
        addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.apply {
                    setVolume(0)
                    loadVideo(videoId, 10f)
                }
            }

            override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
                dura = duration
                super.onVideoDuration(youTubePlayer, duration)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                super.onCurrentSecond(youTubePlayer, second)
                Log.d("TEST_DURA", "dura: $dura seconds = $second")
                if (dura.toInt() - 10 == second.toInt()) youTubePlayer.seekTo(0f)
            }
        })
    }
}

@Composable
fun rememberPlayerView(): YouTubePlayerView {
    val context = LocalContext.current
    val youTubeView = remember {
        YouTubePlayerView(context)
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle, youTubeView) {
        lifecycle.addObserver(youTubeView)
        onDispose {
            lifecycle.removeObserver(youTubeView)
        }
    }
    return youTubeView
}
