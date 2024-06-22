package com.example.domain

import com.example.corenetwork.ApiResult
import com.example.domain.datasource.GetProductsDataSource
import com.example.domain.model.Products
import com.example.domain.usecase.GetProducts
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetProductsTest {
    private val getProductsDataSource: GetProductsDataSource = mockk()
    private val getProducts = GetProducts(getProductsDataSource)

    @Test
    fun `get products usecase success should return ApiResult success`() = runBlocking {
        coEvery { getProductsDataSource.getProducts() } returns ApiResult.SUCCESS(Products(listOf()))

        val result = getProducts.invoke()

        coVerify { getProductsDataSource.getProducts() }
        assert(result is ApiResult.SUCCESS)
    }

    @Test
    fun `get products usecase error should return ApiResult error`() = runBlocking {
        coEvery { getProductsDataSource.getProducts() } returns ApiResult.ERROR("Error")

        val result = getProducts.invoke()

        coVerify { getProductsDataSource.getProducts() }
        assert(result is ApiResult.ERROR)
    }
}