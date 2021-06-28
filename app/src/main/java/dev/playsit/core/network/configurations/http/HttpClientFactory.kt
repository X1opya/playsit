package dev.playsit.core.network.configurations.http

import okhttp3.OkHttpClient

interface HttpClientFactory {
    fun create(): OkHttpClient
}
