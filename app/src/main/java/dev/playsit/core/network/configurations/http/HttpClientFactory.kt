package dev.playsit.core.network.handlers.http

import okhttp3.OkHttpClient

interface HttpClientFactory {
    fun create(): OkHttpClient
}
