package dev.playsit.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.navigation.compose.rememberNavController
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
                    val navController = rememberNavController()
                    MainScreen(discoverViewModel, navController)
//                    MainScreen(discoverViewModel)
                }
            }
        }
        discoverViewModel.getFeed()
    }
}

//@Composable
//@Preview(showBackground = true)
//fun Greeting() {
//    MainScreen(discoverViewModel)
//}
