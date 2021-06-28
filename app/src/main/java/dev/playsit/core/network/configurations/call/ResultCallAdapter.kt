package dev.playsit.core.network.handlers.call

import dev.playsit.core.network.handlers.result.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ResultCallAdapter<R>(private val type: Type) : CallAdapter<R, Call<ApiResult<R>>> {

    override fun responseType() = type

    override fun adapt(call: Call<R>): Call<ApiResult<R>> = ResultCall(call)
}
