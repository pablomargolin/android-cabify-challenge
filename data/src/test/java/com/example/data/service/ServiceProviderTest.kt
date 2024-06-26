package com.example.data.service

import com.example.corenetwork.ServiceBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class ServiceProviderTest {

    private val serviceBuilder: ServiceBuilder = mockk()
    private val retrofit: Retrofit = mockk()
    private lateinit var serviceProvider: ServiceProvider

    @Before
    fun setUp() {
        serviceProvider = ServiceProvider(serviceBuilder)

        every { serviceBuilder.createRetrofitService() } returns retrofit
        every { retrofit.create(ProductsService::class.java) } returns mockk()
    }

    @Test
    fun `provide service should returns product service`() {
        val productsService = mockk<ProductsService>()
        every { retrofit.create(ProductsService::class.java) } returns productsService

        val result = serviceProvider.provideProductsService()

        assertNotNull(result)
        verify { retrofit.create(ProductsService::class.java) }
    }

    @Test
    fun `provide service should create retrofit service`() {
        val retrofitInstance = serviceProvider.provideProductsService()

        assertNotNull(retrofitInstance)

        verify(exactly = 1) { serviceBuilder.createRetrofitService() }
    }
}