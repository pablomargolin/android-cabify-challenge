package com.example.data.service

import com.example.corenetwork.ServiceBuilder
import retrofit2.Retrofit
import javax.inject.Inject

class ServiceProvider @Inject constructor(
    private val serviceBuilder: ServiceBuilder
) {
    private val retrofit: Retrofit by lazy {
        serviceBuilder.createRetrofitService()
    }

    fun provideProductsService(): ProductsService {
        return retrofit.create(ProductsService::class.java)
    }
}