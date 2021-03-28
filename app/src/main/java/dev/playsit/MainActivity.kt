package dev.playsit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dev.playsit.ui.modules.MainScreen
import dev.playsit.ui.theme.PlaysitTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaysitTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background, contentColor = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    MainScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    PlaysitTheme {
        Greeting()
    }
}