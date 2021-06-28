package dev.playsit.core.network.handlers.call

import dev.playsit.core.network.handlers.result.ApiResult
import dev.playsit.core.network.handlers.result.HttpException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

internal class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, ApiResult<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<ApiResult<T>>) = proxy
        .enqueue(ResultCallback(this, callback))

    override fun cloneImpl(): ResultCall<T> = ResultCall(proxy.clone())

    private class ResultCallback<T>(
        private val proxy: ResultCall<T>,
        private val callback: Callback<ApiResult<T>>
    ) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            val result: ApiResult<T> = if (response.isSuccessful) createSuccessResponse(response)
            else {
                val error = HttpException(
                    statusCode = response.code(),
                    statusMessage = response.message(),
                    errorBody = response.errorBody()?.string()
                )
                ApiResult.Failure.HttpError(error)
            }
            callback.onResponse(proxy, Response.success(result))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val result = when (error) {
                is retrofit2.HttpException -> ApiResult.Failure.HttpError(
                    HttpException(error.code(), error.message(), error.response()?.errorBody()?.string(), error)
                )
                is IOException -> ApiResult.Failure.Error(error)
                else -> ApiResult.Failure.Error(error)
            }

            callback.onResponse(proxy, Response.success(result))
        }

        private fun createSuccessResponse(response: Response<T>): ApiResult<T> {
            val body = response.body()
            return if(body == null) ApiResult.Success.Empty
            else {
                ApiResult.Success.HttpResponse(
                    value = response.body() as T,
                    statusCode = response.code(),
                    statusMessage = response.message()
                )
            }
        }
    }

    override fun timeout(): Timeout {
        return Timeout()
    }
}
