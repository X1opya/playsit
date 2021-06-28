package dev.playsit.core.network.configurations.http

import dev.playsit.core.network.configurations.call.ResultAdapterFactory
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiServiceBuilder(private val httpClientFactory: HttpClientFactory) {
    private var converterFactory: Converter.Factory = GsonConverterFactory.create()
    private var targetUrl = ""

    fun setConverterFactory(converterFactory: Converter.Factory) = apply {
        this.converterFactory = converterFactory
    }

    fun setTargetUrl(targetUrl: String) = apply {
        this.targetUrl = targetUrl
    }

    @Throws(IllegalStateException::class)
    open fun <T> build(serviceClass: Class<T>): T {
        val client = httpClientFactory.create()

        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(client)
            .baseUrl(targetUrl)
            .addCallAdapterFactory(ResultAdapterFactory())
            .build()
            .create(serviceClass)
    }
}
