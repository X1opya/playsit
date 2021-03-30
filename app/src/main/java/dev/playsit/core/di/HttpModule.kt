package dev.playsit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.playsit.core.network.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object HttpModule {

    const val TIMEOUT = 30L
    const val BASE_URL = "https://api.playsit.app/"
    const val HEADER_NAME = "secret-hash"
    const val SECRET_KEY = "ce80622bf18fd1f0375bd9e192a62f436a38c7cd"

    @Provides
    fun provideOkhttpClient(
        headerInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(headerInterceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideHeaderInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header(HEADER_NAME, SECRET_KEY)
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient
    ): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

}