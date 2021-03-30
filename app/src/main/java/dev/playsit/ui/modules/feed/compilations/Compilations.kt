package dev.playsit.ui.modules.feed.compilations

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import dev.playsit.model.Compilation
import dev.playsit.model.FeedItem
import dev.playsit.ui.components.gameCards.BigGameCard

@Composable
fun CompilationList(compilation: LazyPagingItems<FeedItem>, loadMore: (Compilation) -> Unit) {
    if(compilation == null) return

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        items(compilation) { index ->
//            if(index == compilation.value?.items?.count()!! - 1) {
//                loadMore(compilation?.value!!)
//            }
//            Log.d("TEST_LAZY", "index $index")
//            Log.d("TEST_LAZY", "count ${compilation.value?.items?.count()}")
            BigGameCard(index!!)
        }
    }
}