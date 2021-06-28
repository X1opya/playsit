package dev.playsit.core.network.handlers.result

class HttpException(
    val statusCode: Int,
    val statusMessage: String? = null,
    val errorBody: String? = null,
    cause: Throwable? = null
) : Exception(null, cause)
