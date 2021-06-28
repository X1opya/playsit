package dev.playsit.core.network.handlers.call

import dev.playsit.core.network.handlers.result.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {

        val rawReturnType: Class<*> = getRawType(returnType)
        if (isCall(rawReturnType) && returnType is ParameterizedType) {
            val callInnerType: Type = getParameterUpperBound(0, returnType)
            if (getRawType(callInnerType) == ApiResult::class.java) {
                // resultType is Call<Result<*>> | callInnerType is Result<*>
                if (callInnerType is ParameterizedType) {
                    val resultInnerType = getParameterUpperBound(0, callInnerType)
                    return ResultCallAdapter<Any?>(resultInnerType)
                }
                return ResultCallAdapter<Nothing>(Nothing::class.java)
            }
        }

        return null
    }

    private fun isCall(type: Class<*>): Boolean = type == Call::class.java
}
