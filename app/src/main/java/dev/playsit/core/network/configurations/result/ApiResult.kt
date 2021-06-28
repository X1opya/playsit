package dev.playsit.core.network.configurations.result

sealed class ApiResult<out T> {

    sealed class Success<T> : ApiResult<T>() {
        abstract val value: T?

        override fun toString() = value?.toString() ?: ""

        data class HttpResponse<T>(
            override val value: T?,
            override val statusCode: Int,
            override val statusMessage: String?
        ) : Success<T>(), ApiResponse

        object Empty : Success<Nothing>() {
            override val value: Nothing? get() = null

            override fun toString() = "Success"
        }
    }

    sealed class Failure<E : Throwable>(open val error: E? = null) : ApiResult<Nothing>() {
        override fun toString() = "Failure($error)"

        class Error(override val error: Throwable) : Failure<Throwable>(error)

        class HttpError(override val error: HttpException) : Failure<HttpException>(error),
            ApiResponse {
            override val statusCode: Int
                get() = error.statusCode

            override val statusMessage: String?
                get() = error.statusMessage

            operator fun component1() = error
            operator fun component2() = statusCode
            operator fun component3() = statusMessage
        }
    }

    sealed class Loading<T> : ApiResult<T>()
}
