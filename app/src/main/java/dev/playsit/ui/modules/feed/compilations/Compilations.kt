package dev.playsit.ui.modules.feed.compilations

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import dev.playsit.model.Compilation
import dev.playsit.ui.components.gameCards.BigGameCard

@Composable
fun CompilationList(compilation: State<Compilation?>, loadMore: (Int, String) -> Unit) {
    if(compilation.value == null) return
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        itemsIndexed(compilation.value?.items!!) { index, item ->
            if(index == compilation.value?.items?.count()!! - 1) {
                loadMore(compilation.value?.items?.count()!!, compilation.value?.slug!!)
            }
            Log.d("TEST_LAZY", "index $index")
            Log.d("TEST_LAZY", "count ${compilation.value?.items?.count()}")
            BigGameCard(compilation.value!!.items[index])
        }
    }
}