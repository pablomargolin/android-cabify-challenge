package com.example.data.mapper

import com.example.data.dto.ProductDto
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductDtoToDomainMapperTest {
    private val mapper = ProductDtoToDomainMapper()

    @Test
    fun `map product dto should return product model`(){
        val dto = ProductDto("code", "name", 5f)

        val result = mapper.map(dto)

        assertEquals(dto.code, result.code)
        assertEquals(dto.name, result.name)
        assertEquals(dto.price, result.price)
    }
}