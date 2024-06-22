package com.example.data.datasource

import com.example.corenetwork.ApiResult
import com.example.corenetwork.ServiceHelper
import com.example.data.dto.ProductDto
import com.example.data.dto.ProductsDto
import com.example.data.mapper.ProductsDtoToDomainMapper
import com.example.domain.datasource.GetProductsDataSource
import com.example.domain.model.Product
import com.example.domain.model.Products
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetProductDataSourceTest {
    private val serviceHelper: ServiceHelper = mockk()
    private val mapper: ProductsDtoToDomainMapper = mockk()
    private lateinit var dataSource: GetProductsDataSource

    @Before
    fun setUp(){
        dataSource = GetProductDataSourceImpl(serviceHelper, mapper)
    }

    @Test
    fun `get products should return ApiResult success with model`() = runBlocking {
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

        val expectedProducts = Products(listOf(Product("voucher", "voucher", 5.0f)))

        every { response.isSuccessful } returns true
        every { response.body() } returns responseBody
        every { responseBody.string() } returns json
        coEvery { serviceHelper.getCabifyService().getProducts() } returns response
        every { mapper.map(any()) } returns expectedProducts

        val result = dataSource.getProducts()

        assertTrue(result is ApiResult.SUCCESS)
        assertEquals(expectedProducts, (result as ApiResult.SUCCESS).result)
    }
}