package com.example.data.service

import com.example.data.dto.ProductsDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductsService {
    @GET("/palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
    suspend fun getProducts(): Response<ProductsDto>
}