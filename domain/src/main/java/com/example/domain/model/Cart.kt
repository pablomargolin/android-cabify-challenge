package com.example.domain.model

data class Cart(
    val products: MutableList<Product>,
    val totalPrice: Float
)
