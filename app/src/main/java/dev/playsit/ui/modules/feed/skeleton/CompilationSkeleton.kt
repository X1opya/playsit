package dev.playsit.ui.modules.feed.skeleton

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.playsit.R
import dev.playsit.ui.components.effects.placeholder

@Composable
@Preview
fun CompilationSkeleton() {
    Column {
        Row(Modifier.padding(25.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(88.dp, 34.dp)
                    .placeholder(true)
            )
            Spacer(Modifier.weight(1f))
            Icon(painterResource(R.drawable.ic_grid), contentDescription = null, tint = Color.White)
        }
        Box(Modifier.size(134.dp, 20.dp).padding(start = 25.dp).placeholder(true))
        Spacer(modifier = Modifier.size(27.dp))

        Row(Modifier.padding(start = 25.dp)) {
            for (index in 0..2) {
                Column {
                    Box(Modifier.size(225.dp, 300.dp).placeholder(true))
                    Spacer(modifier = Modifier.size(15.dp))
                    Box(Modifier.size(225.dp, 24.dp).placeholder(true))
                    Spacer(modifier = Modifier.size(15.dp))
                    Box(Modifier.size(70.dp, 34.dp).placeholder(true))
                }
                Spacer(modifier = Modifier.size(25.dp))
            }
        }
    }
}
