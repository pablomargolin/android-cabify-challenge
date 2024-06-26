package com.example.domain.usecase

import com.example.domain.model.Cart
import com.example.domain.model.Codes
import com.example.domain.model.Product
import javax.inject.Inject

class ApplyDiscounts @Inject constructor() {

    operator fun invoke(cart: Cart): Float {
        val productCount = cart.products.groupingBy { it.code }.eachCount()
        var total = 0f
        productCount.forEach { (code, count) ->
            total += when(code){
                Codes.VOUCHER.value -> voucherDiscount(cart.products, count)
                Codes.TSHIRT.value -> tShirtDiscount(cart.products, count)
                else -> cart.products.filter { it.code == code }.sumOf { it.price?.toDouble() ?: 0.0 }.toFloat()
            }
        }
        return total
    }

    private fun voucherDiscount(products: List<Product>, count: Int): Float {
        val voucherPrice = products.find { it.code == Codes.VOUCHER.value }?.price ?: 0f
        val discountMultiplier = count / 2 + count % 2
        return voucherPrice * discountMultiplier
    }

    private fun tShirtDiscount(products: List<Product>, count: Int): Float {
        val tShirtPrice = products.find { it.code == Codes.TSHIRT.value }?.price ?: 0f
        val discountedPrice = if (count >= 3) tShirtPrice - 1 else tShirtPrice
        return discountedPrice * count
    }
}