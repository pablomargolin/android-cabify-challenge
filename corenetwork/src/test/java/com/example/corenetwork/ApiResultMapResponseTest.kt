package com.example.corenetwork

import com.example.corenetwork.extension.mapResponse
import io.mockk.every
import io.mockk.mockk
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

class ApiResultMapResponseTest {
    @Test
    fun `map response when is success should return ApiResult success`() {
        val response = mockk<Response<ProductsDto>>()

        val expectedResult = ProductsDto(listOf(ProductDto("voucher", "voucher", 5f)))

        every { response.isSuccessful } returns true
        every { response.body() } returns expectedResult

        val result = mapResponse<ProductsDto, ProductsDto>(response) { it }

        assertTrue(result is ApiResult.Success)
        assertEquals(expectedResult, (result as ApiResult.Success).result)
    }

    @Test
    fun `map response when is error should return ApiResult error`() {
        val response = mockk<Response<ProductsDto>>()

        every { response.isSuccessful } returns false
        every { response.body() } returns null

        val result = mapResponse<ProductsDto, ProductsDto>(response) { it }

        assertTrue(result is ApiResult.Error)
        assertEquals("Error getting response", (result as ApiResult.Error).error)
    }

    @Test
    fun `map response when is exception should return ApiResult error`() {
        val responseBody = mockk<ProductsDto>()
        val response = mockk<Response<ProductsDto>>()

        every { response.isSuccessful } returns true
        every { response.body() } throws Exception("exception")

        val result = mapResponse<ProductsDto, ProductsDto>(response) { it }

        assertTrue(result is ApiResult.Error)
        assertEquals("exception", (result as ApiResult.Error).error)
    }
}

data class ProductDto(
    val code: String?,
    val name: String?,
    val price: Float?
)

data class ProductsDto(
    val products: List<ProductDto>
)