package dev.playsit.ui.components.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import dev.playsit.ui.theme.GrayTextColor
import dev.playsit.ui.theme.WhiteTextColor

@Composable
fun DefaultBoldText(text: String, fontSize: TextUnit) {
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = fontSize, color = WhiteTextColor)
}

@Composable
fun GameTitle(text: String) {
    DefaultBoldText(text = text, fontSize = 16.sp)
}

@Composable
fun CategoryTitle(text: String) {
    DefaultBoldText(text = text, fontSize = 25.sp)
}

@Composable
fun GenreText(text: String) {
    DefaultBoldText(text = text, fontSize = 14.sp)
}

@Composable
fun DefaultGrayText(text: String, fontSize: TextUnit) {
    Text(text = text, fontWeight = FontWeight.Normal, fontSize = fontSize, color = GrayTextColor)
}

@Composable
fun DescriptionText(text: String) {
    DefaultGrayText(text = text, fontSize = 14.sp)
}
