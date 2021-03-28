package dev.playsit.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBarr(title: String) {
    TopAppBar(
        title = {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = title,
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
                )
            }
        },
        modifier = Modifier.height(80.dp)
    )
}