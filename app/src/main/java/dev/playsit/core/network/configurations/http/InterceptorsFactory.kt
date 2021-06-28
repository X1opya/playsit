package dev.playsit.core.network.handlers.http

import okhttp3.Interceptor

interface InterceptorsFactory {
    fun create(): List<Interceptor>
}
