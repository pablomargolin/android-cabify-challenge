package com.example.app

import com.example.cabifychallenge.presentation.viewmodel.GetProductsState
import com.example.cabifychallenge.presentation.viewmodel.MainActivityViewModel
import com.example.corenetwork.ApiResult
import com.example.domain.CartRepository
import com.example.domain.model.Product
import com.example.domain.model.Products
import com.example.domain.usecase.GetProducts
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    private val getProducts: GetProducts = mockk()
    private val cartRepository: CartRepository = mockk()
    private val viewModel = MainActivityViewModel(getProducts, cartRepository)

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `get products success should put ui state with data`() {
        coEvery { getProducts.invoke()} returns  ApiResult.SUCCESS(Products(emptyList()))

        viewModel.getProducts()

        coVerify { getProducts.invoke() }
        assert(viewModel.uiState.value is GetProductsState.GetProductsSuccess)
        assertEquals(0, viewModel.componentsViewModel.size)
    }

    @Test
    fun `get products error should put ui state on error`(){
        coEvery { getProducts.invoke() } returns ApiResult.ERROR("Error")

        viewModel.getProducts()

        coVerify { getProducts.invoke() }
        assert(viewModel.uiState.value is GetProductsState.GetProductsError)
    }

    @Test
    fun `add product to cart should add product to cart repository`(){
        val product = Product("code", "name", 5f)
        every { cartRepository.addProduct(any()) } answers {}
        every { cartRepository.getCart() } returns mockk()

        viewModel.productAdded(product)

        verify { cartRepository.addProduct(product) }
        verify { cartRepository.getCart() }
    }

    @Test
    fun `remove product from cart should remove product from cart repository`(){
        val product = Product("code", "name", 5f)

        every { cartRepository.removeProduct(any()) } answers {}
        every { cartRepository.getCart() } returns mockk()

        viewModel.productRemoved(product)

        verify { cartRepository.removeProduct(product) }
        verify { cartRepository.getCart() }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}