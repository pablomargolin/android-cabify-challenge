package com.example.data.datasource

import com.example.corenetwork.ApiResult
import com.example.data.service.ServiceProvider
import com.example.corenetwork.extension.mapResponse
import com.example.data.dto.ProductsDto
import com.example.data.mapper.ProductsDtoToDomainMapper
import com.example.domain.datasource.ProductsDataSource
import com.example.domain.model.Products
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val serviceProvider: ServiceProvider,
    private val mapper: ProductsDtoToDomainMapper
): ProductsDataSource {
    override suspend fun getProducts(): ApiResult<Products> {
        runCatching {
            val response = serviceProvider.provideProductsService().getProducts()
            val result = mapResponse<ProductsDto, Products>(response, successResponse = { mapper.map(it) })
            return result
        }
            .getOrElse {
            return ApiResult.Error("Error getting products")
        }
    }
}