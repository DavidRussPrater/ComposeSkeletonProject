package com.spatiumapps.composeskeletonproject.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.spatiumapps.composeskeletonproject.data.api.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val baseUrl = "https://base.url/"
    private val contentType = "application/json".toMediaType()

    @Provides
    fun providesBaseUrl() = baseUrl

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient { // debug ON
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .readTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseURL: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()

    @Singleton
    @Provides
    fun provideRestaurantService(retrofit: Retrofit): NetworkService =
        retrofit.create(
            NetworkService::class.java
        )
}
