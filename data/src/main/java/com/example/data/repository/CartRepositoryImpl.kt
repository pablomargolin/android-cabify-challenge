package com.example.data.repository

import com.example.domain.CartRepository
import com.example.domain.model.Cart
import com.example.domain.model.Product
import com.example.domain.usecase.ApplyDiscounts
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val applyDiscounts: ApplyDiscounts
): CartRepository {
    private var cart: Cart? = null

    private fun createCart(product: Product){
        cart = Cart(
            mutableListOf(product),
            product.price ?: 0f
        )
    }

    override fun addProduct(product: Product) {
        cart?.products?.add(product) ?: createCart(product)
        calculateTotalPrice()
    }

    override fun getCart(): Cart? {
        return cart
    }

    override fun removeProduct(product: Product) {
        cart?.products?.remove(product)
        calculateTotalPrice()
        cart = cart.takeIf { it?.products?.isNotEmpty() == true }
    }

    override fun calculateTotalPrice(): Float {
        cart?.let { currentCart ->
            cart = currentCart.copy(
                totalPrice = applyDiscounts(currentCart)
            )
        }
        return cart?.totalPrice ?: 0f
    }
}