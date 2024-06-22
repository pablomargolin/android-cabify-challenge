package com.example.domain.usecase

import com.example.corenetwork.ApiResult
import com.example.domain.datasource.GetProductsDataSource
import com.example.domain.model.Product
import com.example.domain.model.Products
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val getProductsDataSource: GetProductsDataSource
) {
    suspend operator fun invoke(): ApiResult<Products> {
        return getProductsDataSource.getProducts()
    }
}