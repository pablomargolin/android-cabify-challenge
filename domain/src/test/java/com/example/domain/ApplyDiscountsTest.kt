package com.example.domain

import com.example.domain.model.Cart
import com.example.domain.model.Product
import com.example.domain.usecase.ApplyDiscounts
import org.junit.Assert.assertEquals
import org.junit.Test

class ApplyDiscountsTest {
    private val applyDiscounts = ApplyDiscounts()

    @Test
    fun `apply discounts with VOUCHER code products should apply 2x1`() {
        val vouchers = mutableListOf(
            Product("VOUCHER", "voucher", 5f),
            Product("VOUCHER", "Voucher", 5f)
        )

        val cartVouchers = Cart(
            vouchers,
            10f
        )
        val total = applyDiscounts.invoke(cartVouchers)

        assertEquals(5.0f, total)
    }

    @Test
    fun `apply discounts with 3 TSHIRT code should apply discount per unit`() {
        val tshirts = mutableListOf(
            Product("TSHIRT", "tshirt", 20f),
            Product("TSHIRT", "tshirt", 20f),
            Product("TSHIRT", "tshirts", 20f)
        )

        val cartTshirts = Cart(
            tshirts,
            60f
        )
        val total = applyDiscounts.invoke(cartTshirts)

        assertEquals(57f, total)
    }

    @Test
    fun `apply discounts with less than 3 TSHIRT code should not apply discount per unit`() {
        val tshirts = mutableListOf(
            Product("TSHIRT", "tshirt", 20f),
            Product("TSHIRT", "tshirt", 20f)
        )

        val cartTshirts = Cart(
            tshirts,
            60f
        )

        val total = applyDiscounts.invoke(cartTshirts)

        assertEquals(40f, total)
    }

    @Test
    fun `apply discounts with mixed products should apply appropriate discounts`() {
        val products = mutableListOf(
            Product("VOUCHER", "voucher", 5f),
            Product("VOUCHER", "voucher", 5f),
            Product("TSHIRT", "tshirt", 20f),
            Product("TSHIRT", "tshirt", 20f),
            Product("TSHIRT", "tshirt", 20f),
            Product("MUG", "mug", 7.5f)
        )
        val cart = Cart(
            products,
            77.5f
        )

        val total = applyDiscounts.invoke(cart)

        assertEquals(69.5f, total)
    }

    @Test
    fun `apply discounts with no discountable products should return correct total`() {
        val products = mutableListOf(
            Product("MUG", "Coffee Mug", 7.5f),
            Product("MUG", "Coffee Mug", 7.5f)
        )
        val cart = Cart(
            products,
            15f
        )

        val total = applyDiscounts.invoke(cart)

        assertEquals(15f, total)
    }
}