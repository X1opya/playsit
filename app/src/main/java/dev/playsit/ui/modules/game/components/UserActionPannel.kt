package dev.playsit.ui.modules.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.playsit.R
import dev.playsit.ui.theme.UnSelectTabColor

@Composable
fun UserActionPanel() {
    Row(horizontalArrangement = Arrangement.Center) {
        RoundedButton(
            color = UnSelectTabColor,
            icon = R.drawable.ic_clock,
            stringResource(id = R.string.willPlay)
        )
        Spacer(modifier = Modifier.size(30.dp))
        RoundedButton(
            color = UnSelectTabColor, icon = R.drawable.ic_accept,
            stringResource(id = R.string.played)
        )
        Spacer(modifier = Modifier.size(30.dp))
        RoundedButton(
            color = UnSelectTabColor, icon = R.drawable.ic_plus,
            stringResource(id = R.string.save)
        )
    }
}

@Composable
fun RoundedButton(color: Color, icon: Int, text: String, onClick: (() -> Unit)? = null) {
    Column(Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(70.dp)
                .background(color, CircleShape)
                .clip(CircleShape)
                .clickable { onClick?.invoke() }
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.White
            )
        }
        Text(text = text, Modifier.padding(top = 4.dp), color = Color.White)
    }
}

@Composable
@Preview
fun PreviewActionPanel() {
    UserActionPanel()
}
