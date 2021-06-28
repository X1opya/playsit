package dev.playsit.core.network.configurations.handler

import dev.playsit.core.network.configurations.result.ApiResult

suspend fun <T> sendRequest(listener: ApiResultListener<T>, request: suspend () -> ApiResult<T?>) {
    when(val result = request.invoke()) {
        is ApiResult.Success.HttpResponse -> listener.onSuccess(result.value)
        is ApiResult.Success.Empty -> listener.onSuccess(null)
        is ApiResult.Failure.HttpError -> {
            val (error, statusCode, statusMessage) = result
            listener.onHttpError(error, statusCode, statusMessage)
        }
        is ApiResult.Failure.Error -> listener.onError(result.error)
    }
}
