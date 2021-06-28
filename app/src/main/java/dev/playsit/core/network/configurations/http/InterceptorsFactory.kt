package dev.playsit.core.network.configurations.http

import okhttp3.Interceptor

interface InterceptorsFactory {
    fun create(): List<Interceptor>
}
