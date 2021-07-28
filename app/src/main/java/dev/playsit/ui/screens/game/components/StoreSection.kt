package dev.playsit.ui.screens.game.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.playsit.R
import dev.playsit.R.string.lastUpdateText
import dev.playsit.core.utils.toReadableDate
import dev.playsit.dto.GameImageConverter
import dev.playsit.dto.Prices
import dev.playsit.dto.Store
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.components.text.DefaultGrayText
import dev.playsit.ui.theme.GrayTextColor
import dev.playsit.ui.theme.UnSelectTabColor

@Composable
fun StoreSection(prices: Prices) {
    Column(modifier = Modifier.padding(horizontal = 25.dp)) {
        CategoryTitleText(text = stringResource(id = R.string.store))
        Spacer(modifier = Modifier.size(25.dp))
        prices.stores.forEach {
            StoreItem(
                store = it.store,
                title = it.type,
                iconId = GameImageConverter.getStoreIcon(it.type)
            )
            Spacer(modifier = Modifier.size(15.dp))
        }
        Spacer(modifier = Modifier.size(10.dp))
        DefaultGrayText(text = stringResource(id = lastUpdateText) + " " + prices.lastUpdatedTime.toReadableDate())
    }
}

@Composable
fun StoreItem(store: Store?, title: String, iconId: Int) {
    val context = LocalContext.current
    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(store?.url))
    Card(modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .height(60.dp)
        .fillMaxWidth()
        .clickable { context.startActivity(webIntent) }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    UnSelectTabColor
                )
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            Icon(
                painterResource(iconId),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(19.dp))
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp),
                horizontalAlignment = Alignment.End
            ) {
                store?.discount?.let {
                    Box(
                        modifier = Modifier.wrapContentSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Divider(
                            color = GrayTextColor,
                            thickness = 1.dp,
                            modifier = Modifier.width(40.dp)
                        )
                        Text(
                            text = it,
                            color = GrayTextColor,
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        )
                    }
                }
                Text(
                    text = store?.price ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}
