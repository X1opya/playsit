package dev.playsit.core.network.configurations.call

import dev.playsit.core.network.configurations.result.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ResultCallAdapter<R>(private val type: Type) : CallAdapter<R, Call<ApiResult<R>>> {

    override fun responseType() = type

    override fun adapt(call: Call<R>): Call<ApiResult<R>> = ResultCall(call)
}
