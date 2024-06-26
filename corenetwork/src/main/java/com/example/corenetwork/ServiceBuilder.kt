package com.example.corenetwork

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ServiceBuilder @Inject constructor(
    private val gsonBuilder: GsonBuilder
) {
    fun createRetrofitService(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        val builder = gsonBuilder
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(builder))
            .client(client)
            .build()
    }

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com"
        private const val CONNECTION_TIMEOUT = 60L
    }
}