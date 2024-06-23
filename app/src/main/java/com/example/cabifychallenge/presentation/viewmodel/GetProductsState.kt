package com.example.cabifychallenge.presentation.viewmodel

import com.example.domain.model.Products

sealed class GetProductsState {
    class GetProductsSuccess(val products: Products): GetProductsState()
    object GetProductsError: GetProductsState()
}