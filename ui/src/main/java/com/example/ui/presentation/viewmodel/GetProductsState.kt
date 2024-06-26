package com.example.ui.presentation.viewmodel

sealed class GetProductsState {
    object GetProductsSuccess: GetProductsState()
    object GetProductsError: GetProductsState()
}