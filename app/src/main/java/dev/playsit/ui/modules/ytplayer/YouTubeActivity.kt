package dev.playsit.ui.modules.ytplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.updatePadding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dev.playsit.R
import dev.playsit.databinding.ActivityPlayerBinding

class YouTubeActivity : AppCompatActivity(R.layout.activity_player) {

    private val binding by viewBinding(ActivityPlayerBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
//    setTheme(R.style.Theme_PlaysIt)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        intent.extras?.getString(VIDEO_ID)?.let { videoId ->
            binding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            binding.root.setOnApplyWindowInsetsListener { _, insets ->
                binding.ytPlayer.updatePadding(
                    top = insets.systemWindowInsetTop,
                    bottom = insets.systemWindowInsetBottom
                )
                insets
            }
            binding.ytPlayer.apply {
                lifecycle.addObserver(this)
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        }
    }

    companion object {
        const val VIDEO_ID = "video_id"

        fun navigateToPlayer(context: Context, videoId: String) {
            val intent = Intent(context, YouTubeActivity::class.java)
            intent.putExtras(bundleOf(YouTubeActivity.VIDEO_ID to videoId))
            context.startActivity(intent)
        }
    }
}
