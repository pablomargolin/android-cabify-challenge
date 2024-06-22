package com.example.data.datasource

import com.example.corenetwork.ApiResult
import com.example.corenetwork.ServiceHelper
import com.example.corenetwork.extension.mapResponse
import com.example.data.dto.ProductsDto
import com.example.data.mapper.ProductsDtoToDomainMapper
import com.example.domain.datasource.GetProductsDataSource
import com.example.domain.model.Products
import javax.inject.Inject

class GetProductDataSourceImpl @Inject constructor(
    private val serviceHelper: ServiceHelper,
    private val mapper: ProductsDtoToDomainMapper
): GetProductsDataSource {
    override suspend fun getProducts(): ApiResult<Products> {
        val response = serviceHelper.getCabifyService().getProducts()
        val result = mapResponse<ProductsDto, Products>(response, successResponse = { mapper.map(it) })
        return result
    }
}