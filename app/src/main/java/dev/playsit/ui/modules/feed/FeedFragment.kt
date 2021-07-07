package dev.playsit.ui.modules.feed

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import dev.playsit.R
import dev.playsit.ui.components.CustomAppBar
import dev.playsit.ui.components.pager.FeedPager
import dev.playsit.ui.modules.feed.boards.BoardFragment
import dev.playsit.ui.modules.feed.compilations.CompilationProvider
import dev.playsit.ui.modules.feed.compilations.CompilationsFragment

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun FeedFragment(
    discoverViewModel: DiscoverViewModel,
    compilations: State<MutableList<CompilationProvider>?>,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CustomAppBar(title = stringResource(id = R.string.discoverTitle))
        val loading = discoverViewModel.isFeedLoaded.collectAsState()
        FeedPager(
            { _, modifier ->
                CompilationsFragment(
                        compilations,
                        navController,
                        modifier = modifier,
                        loading
                    )
            },
            { _, modifier ->
                BoardFragment(discoverViewModel, modifier = modifier)
            }
        )
    }
}
