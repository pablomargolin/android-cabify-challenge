package com.example.domain.datasource

import com.example.corenetwork.ApiResult
import com.example.domain.model.Product
import com.example.domain.model.Products

interface GetProductsDataSource {
    suspend fun getProducts(): ApiResult<Products>
}