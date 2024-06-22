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

        val responseBody = mockk<ResponseBody>()
        val response = mockk<Response<ResponseBody>>()
        val json = """
        {
          "products": [
            {
              "code": "voucher",
              "name": "voucher",
              "price": 5
            }
          ]
        }
        """
        val expectedResult = ProductsDto(listOf(ProductDto("voucher", "voucher", 5f)))

        every { response.isSuccessful } returns true
        every { response.body() } returns responseBody
        every { responseBody.string() } returns json

        val result = mapResponse<ProductsDto, ProductsDto>(response) { it }

        assertTrue(result is ApiResult.SUCCESS)
        assertEquals(expectedResult, (result as ApiResult.SUCCESS).result)
    }

    @Test
    fun `map response when is error should return ApiResult error`() {
        val response = mockk<Response<ResponseBody>>()

        every { response.isSuccessful } returns false
        every { response.body() } returns null

        val result = mapResponse<List<ProductDto>, List<ProductDto>>(response) { it }

        assertTrue(result is ApiResult.ERROR)
        assertEquals("Error getting response", (result as ApiResult.ERROR).error)
    }

    @Test
    fun `map response when is exception should return ApiResult error`() {
        val responseBody = mockk<ResponseBody>()
        val response = mockk<Response<ResponseBody>>()

        every { response.isSuccessful } returns true
        every { response.body() } returns responseBody
        every { responseBody.string() } throws Exception("exception")

        val result = mapResponse<List<ProductDto>, List<ProductDto>>(response) { it }

        assertTrue(result is ApiResult.ERROR)
        assertEquals("exception", (result as ApiResult.ERROR).error)
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