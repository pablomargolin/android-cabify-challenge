package com.example.data.datasource

import com.example.corenetwork.ApiResult
import com.example.data.dto.ProductsDto
import com.example.data.service.ServiceProvider
import com.example.data.mapper.ProductsDtoToDomainMapper
import com.example.domain.datasource.ProductsDataSource
import com.example.domain.model.Product
import com.example.domain.model.Products
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetProductDataSourceTest {
    private val serviceProvider: ServiceProvider = mockk()
    private val mapper: ProductsDtoToDomainMapper = mockk()
    private lateinit var dataSource: ProductsDataSource

    @Before
    fun setUp(){
        dataSource = ProductRemoteDataSource(serviceProvider, mapper)
    }

    @Test
    fun `get products should return ApiResult success with model`() = runBlocking {
        val responseBody = mockk<ProductsDto>()
        val response = mockk<Response<ProductsDto>>()

        val expectedProducts = Products(listOf(Product("voucher", "voucher", 5.0f)))

        every { response.isSuccessful } returns true
        every { response.body() } returns responseBody
        coEvery { serviceProvider.provideProductsService().getProducts() } returns response
        every { mapper.map(any()) } returns expectedProducts

        val result = dataSource.getProducts()

        assertTrue(result is ApiResult.Success)
        assertEquals(expectedProducts, (result as ApiResult.Success).result)
    }
}