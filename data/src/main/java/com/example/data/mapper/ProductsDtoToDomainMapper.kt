package com.example.data.mapper

import com.example.data.dto.ProductsDto
import com.example.domain.model.Product
import com.example.domain.model.Products
import javax.inject.Inject

class ProductsDtoToDomainMapper @Inject constructor(
    private val productDtoToDomainMapper: ProductDtoToDomainMapper
) {
    fun map(productsDto: ProductsDto): Products{
        val list = mutableListOf<Product>()
        productsDto.products.forEach {
            list.add(productDtoToDomainMapper.map(it))
        }
        return Products(list)
    }
}