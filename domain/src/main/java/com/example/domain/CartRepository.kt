package com.example.domain

import com.example.domain.model.Cart
import com.example.domain.model.Product

interface CartRepository {
    fun addProduct(product: Product)
    fun getCart(): Cart?
    fun removeProduct(product: Product)
    fun calculateTotalPrice(): Float
}