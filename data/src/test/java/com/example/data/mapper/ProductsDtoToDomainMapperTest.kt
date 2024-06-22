package com.example.data.mapper

import com.example.data.dto.ProductDto
import com.example.data.dto.ProductsDto
import com.example.domain.model.Product
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsDtoToDomainMapperTest {
    private val productDtoMapper: ProductDtoToDomainMapper = mockk()
    private lateinit var mapper: ProductsDtoToDomainMapper

    @Before
    fun setUp(){
        mapper = ProductsDtoToDomainMapper(productDtoMapper)
    }

    @Test
    fun `map products dto should return products model with product model`(){
        every { productDtoMapper.map(any()) } returns Product("code", "name", 5f)

        val result = mapper.map(ProductsDto(listOf(ProductDto("code", "name", 5f))))

        assertEquals(1, result.products.size)
        assertEquals("code", result.products.first().code)
        assertEquals("name", result.products.first().name)
        assertEquals(5f, result.products.first().price)
    }
}