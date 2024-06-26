package com.example.domain.usecase

import com.example.corenetwork.ApiResult
import com.example.domain.datasource.ProductsDataSource
import com.example.domain.model.Products
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val productsDataSource: ProductsDataSource
) {
    suspend operator fun invoke(): ApiResult<Products> {
        return productsDataSource.getProducts()
    }
}