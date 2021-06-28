package dev.playsit.core.network.handlers.handler

import dev.playsit.core.network.handlers.result.HttpException

interface ApiResultListener<T> {
    fun onSuccess(data: T?)
    fun onError(error: Throwable)
    fun onHttpError(err: HttpException, statusCode: Int, statusMessage: String?)
}
