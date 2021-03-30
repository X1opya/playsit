package dev.playsit.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.playsit.model.FeedItem
import java.lang.Exception

class CompilationSource(private val repository: FeedRepository, val slug: String) :
    PagingSource<Int, FeedItem>() {

    override fun getRefreshKey(state: PagingState<Int, FeedItem>): Int? = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedItem> {
        return try {
            var currentOffset: Int? = params.key ?: 0
            LoadResult.Page
            val compilations =
                repository.getCustomCompilation(slug, currentOffset!!)
            if (compilations?.isLast == true) currentOffset = null
            LoadResult.Page(
                compilations!!.items,
                if(currentOffset == 0) null else currentOffset?.minus(10),
                currentOffset?.plus(10)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true
}