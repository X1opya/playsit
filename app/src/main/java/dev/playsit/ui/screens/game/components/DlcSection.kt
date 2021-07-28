package dev.playsit.ui.screens.game.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.playsit.R
import dev.playsit.dto.Dlc
import dev.playsit.ui.components.gameCards.GameCard
import dev.playsit.ui.components.imageContainers.ImageType
import dev.playsit.ui.components.text.CategoryTitleText
import dev.playsit.ui.theme.BaseAppDimen

@Composable
fun DlcSection(dlcList: List<Dlc>) {
    Column {
        Spacer(modifier = Modifier.size(40.dp))
        CategoryTitleText(
            text = stringResource(id = R.string.dlc),
            modifier = Modifier.padding(horizontal = BaseAppDimen)
        )
        Spacer(modifier = Modifier.size(BaseAppDimen))
        Row() {
            dlcList.forEachIndexed {index, item ->
                if (index == 0) Spacer(modifier = Modifier.size(BaseAppDimen))
                GameCard(feedItem = item, cardType = ImageType.Small, hasGenre = false) {}
                Spacer(modifier = Modifier.size(BaseAppDimen))
            }
        }
    }
}
