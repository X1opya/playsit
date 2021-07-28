package dev.playsit.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.playsit.auth.GoogleAuth
import dev.playsit.ui.screens.MainScreen
import dev.playsit.ui.screens.feed.DiscoverViewModel
import dev.playsit.ui.theme.PlaysitTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val discoverViewModel by viewModels<DiscoverViewModel>()
    private val googleAuth by lazy { GoogleAuth(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        googleAuth.auth()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        googleAuth.onResult(requestCode, data)
    }
}

//@Composable
//@Preview(showBackground = true)
//fun Greeting() {
//    MainScreen(discoverViewModel)
//}
