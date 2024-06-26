package com.example.corenetwork

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceBuilderTest {

    private val gsonBuilder: GsonBuilder = mockk()
    private lateinit var serviceBuilder: ServiceBuilder

    @Before
    fun setUp(){
        serviceBuilder = ServiceBuilder(gsonBuilder)
    }

    @Test
    fun `create retrofit service should return not null service`(){
        every { gsonBuilder.create() } returns mockk()

        val service = serviceBuilder.createRetrofitService()

        assertNotNull(service)
        verify { gsonBuilder.create() }
    }

    @Test
    fun `create retrofit service should create okhttp client with correct timeout`() {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val client = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(mockk<Gson>()))
            .client(client)
            .build()

        assertNotNull(retrofit)
    }

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com"
        private const val CONNECTION_TIMEOUT = 60L
    }
}