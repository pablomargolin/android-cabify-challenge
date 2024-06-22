package com.example.data.mapper

import com.example.data.dto.ProductDto
import com.example.domain.model.Product
import javax.inject.Inject

class ProductDtoToDomainMapper @Inject constructor() {
    fun map(productDto: ProductDto): Product {
        return Product(
            productDto.code,
            productDto.name,
            productDto.price
        )
    }
}