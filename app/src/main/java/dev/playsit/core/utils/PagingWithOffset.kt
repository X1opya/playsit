package dev.playsit.core.utils

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.playsit.core.network.configurations.result.ApiResult
import dev.playsit.core.network.configurations.result.asFailure
import java.lang.Exception

class PagingWithOffset<OUT : Any, IN, MODEL : BaseOffsetModel<OUT, IN>>(
    private val model: MODEL,
    private val param: IN
) : PagingSource<Int, ApiResult<List<OUT>>>() {
    override fun getRefreshKey(state: PagingState<Int, ApiResult<List<OUT>>>): Int? {
        val lastData = state.lastItemOrNull()
        return if (lastData is ApiResult.Success && lastData.value != null && lastData.value!!.size < 5) 5
        else null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResult<List<OUT>>> {
        return try {
            var currentOffset: Int? = params.key ?: 0
            LoadResult.Page
            val data =
                model.invoke(currentOffset!!, param)
            Log.d("TEST_LOAD", "key $currentOffset")
            if (data is ApiResult.Success && !data.value.isNullOrEmpty()) {
                LoadResult.Page(
                    data.value!!,
                    if (currentOffset == 0) null else currentOffset?.minus(10),
                    currentOffset?.plus(10)
                )
            } else LoadResult.Error(Throwable(data.asFailure().error))
            LoadResult.Error(Throwable("asd"))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
