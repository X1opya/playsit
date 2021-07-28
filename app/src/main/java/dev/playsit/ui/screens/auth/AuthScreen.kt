package dev.playsit.ui.screens.auth

import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import dev.playsit.R
import dev.playsit.ui.components.text.DefaultBoldText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AuthScreen() {
    BottomSheetScaffold(sheetShape = MaterialTheme.shapes.medium,sheetContent = {
        DefaultBoldText(text = stringResource(id = R.string.welcome), fontSize = 25.sp)

    }) {

    }
}
