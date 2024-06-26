package com.example.domain.datasource

import com.example.corenetwork.ApiResult
import com.example.domain.model.Products

interface ProductsDataSource {
    suspend fun getProducts(): ApiResult<Products>
}