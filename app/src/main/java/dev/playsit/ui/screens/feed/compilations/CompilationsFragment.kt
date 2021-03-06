package dev.playsit.ui.screens.feed.compilations

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.playsit.core.navigations.Screens
import dev.playsit.core.navigations.navigateToGameScreen

@Composable
fun CompilationsFragment(
    compilations: State<MutableList<CompilationProvider>?>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loading: State<Boolean>
) {
    val context = LocalContext.current
    Column(
        Modifier
            .wrapContentHeight()
            .padding(top = 25.dp)
            .then(modifier)
    ) {
        if (loading.value) {
            CompilationList(null, true, loading.value) {}
            Spacer(modifier = Modifier.size(40.dp))
            CompilationList(null, true, loading.value) {}
        } else {
            compilations.value?.forEachIndexed { index, list ->
                if (list.type == "games") {
                    CompilationList(list, index == 0, onCategoryClick = {
                        navController.navigate(Screens.toGameList(index = index))
                    }) {
                        navController.navigateToGameScreen(it)
                    }
                } else if (list.type == "videos") {
                    VideCompilationList(list) {
                        navController.navigate(Screens.toVideoList())
                    }
                }
                Spacer(modifier = Modifier.size(40.dp))
            }
        }
    }
}
