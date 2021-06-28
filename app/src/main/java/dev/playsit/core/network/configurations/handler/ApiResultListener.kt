package dev.playsit.core.network.configurations.handler

import dev.playsit.core.network.configurations.result.HttpException

interface ApiResultListener<T> {
    fun onSuccess(data: T?)
    fun onError(error: Throwable)
    fun onHttpError(err: HttpException, statusCode: Int, statusMessage: String?)
}
