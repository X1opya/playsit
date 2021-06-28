package dev.playsit.core.network.handlers.result

val <T> ApiResult<T>.isSuccess: Boolean get() = this is ApiResult.Success

fun <T> ApiResult<T>.asSuccess(): ApiResult.Success<T> = this as ApiResult.Success<T>

val <T> ApiResult<T>.isFailure: Boolean get() = this is ApiResult.Failure<*>

fun <T> ApiResult<T>.asFailure(): ApiResult.Failure<*> = this as ApiResult.Failure<*>
