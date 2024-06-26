package com.example.domain

import com.example.corenetwork.ApiResult
import com.example.domain.datasource.ProductsDataSource
import com.example.domain.model.Products
import com.example.domain.usecase.GetProducts
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetProductsTest {
    private val productsDataSource: ProductsDataSource = mockk()
    private val getProducts = GetProducts(productsDataSource)

    @Test
    fun `get products usecase success should return ApiResult success`() = runBlocking {
        coEvery { productsDataSource.getProducts() } returns ApiResult.Success(Products(listOf()))

        val result = getProducts.invoke()

        coVerify { productsDataSource.getProducts() }
        assert(result is ApiResult.Success)
    }

    @Test
    fun `get products usecase error should return ApiResult error`() = runBlocking {
        coEvery { productsDataSource.getProducts() } returns ApiResult.Error("Error")

        val result = getProducts.invoke()

        coVerify { productsDataSource.getProducts() }
        assert(result is ApiResult.Error)
    }
}