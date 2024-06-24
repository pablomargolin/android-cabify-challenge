package com.example.cabifychallenge.presentation.viewmodel

sealed class GetProductsState {
    object GetProductsSuccess: GetProductsState()
    object GetProductsError: GetProductsState()
}