package dev.playsit.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dev.playsit.ui.modules.MainScreen
import dev.playsit.ui.modules.feed.DiscoverViewModel
import dev.playsit.ui.theme.PlaysitTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val discoverViewModel by viewModels<DiscoverViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaysitTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.background
                ) {
                    MainScreen(discoverViewModel)
                }
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun Greeting() {
//    MainScreen(discoverViewModel)
//}
