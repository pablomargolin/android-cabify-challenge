package com.example.data.repository

import com.example.domain.model.Product
import com.example.domain.usecase.ApplyDiscounts
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CartRepositoryTest {
    private val applyDiscounts: ApplyDiscounts = ApplyDiscounts()
    private val cartRepository = CartRepositoryImp(applyDiscounts)

    val product = Product("code", "name", 1f)
    val product2 = Product("code", "name", 1f)

    val voucher = Product("VOUCHER", "voucher", 5f)
    val tshirt = Product("TSHIRT", "tshirt", 20f)
    val mug = Product("MUG", "mug", 7.5f)

    @Test
    fun `get cart before create one should return cart null`(){
        assertNull(cartRepository.getCart())
    }

    @Test
    fun `add product should return cart with one product`(){
        cartRepository.addProduct(product)

        val cart = cartRepository.getCart()

        assertEquals(1, cart?.products?.size)
        assertEquals(product, cart?.products?.first())
    }

    @Test
    fun `remove product to cart with 2 products should return one product`(){
        cartRepository.addProduct(product)
        cartRepository.addProduct(product2)
        cartRepository.removeProduct(product2)

        val cart = cartRepository.getCart()

        assertEquals(1, cart?.products?.size)
        assertEquals(product, cart?.products?.first())
    }

    @Test
    fun `remove product to cart with 1 product should return null cart`(){
        cartRepository.addProduct(product)
        cartRepository.removeProduct(product)

        assertNull(cartRepository.getCart())
    }

    @Test
    fun `add 2 vouchers should return 2x1 price`(){
        cartRepository.addProduct(voucher)
        cartRepository.addProduct(voucher)

        assertEquals(5f, cartRepository.totalPrice())
    }

    @Test
    fun `add 3 tshirts should return discount price per unit`(){
        cartRepository.addProduct(tshirt)
        cartRepository.addProduct(tshirt)
        cartRepository.addProduct(tshirt)

        assertEquals(57f, cartRepository.totalPrice())
    }

    @Test
    fun `add mugs should return normal price`(){
        cartRepository.addProduct(mug)
        cartRepository.addProduct(mug)

        assertEquals(15f, cartRepository.totalPrice())
    }

    @Test
    fun `get total price without products should return 0f`(){
        assertEquals(0f, cartRepository.totalPrice())
    }
}