package dev.playsit.ui.components.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import dev.playsit.ui.theme.GrayTextColor
import dev.playsit.ui.theme.WhiteTextColor

@Composable
fun DefaultBoldText(text: String, fontSize: TextUnit, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
        color = WhiteTextColor,
        modifier = Modifier.then(modifier),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}

@Composable
fun GameTitle(text: String, modifier: Modifier = Modifier) {
    DefaultBoldText(text = text, fontSize = 16.sp, modifier = Modifier.then(modifier))
}

@Composable
fun CategoryTitle(text: String, modifier: Modifier = Modifier) {
    DefaultBoldText(text = text, fontSize = 25.sp, modifier = Modifier.then(modifier))
}

@Composable
fun GenreText(text: String, modifier: Modifier = Modifier) {
    DefaultBoldText(text = text, fontSize = 14.sp, modifier = Modifier.then(modifier))
}

@Composable
fun DefaultGrayText(text: String, fontSize: TextUnit, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.Normal,
        fontSize = fontSize,
        color = GrayTextColor,
        modifier = Modifier.then(modifier),
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun DescriptionText(text: String, modifier: Modifier = Modifier) {
    DefaultGrayText(text = text, fontSize = 14.sp, modifier = Modifier.then(modifier))
}
