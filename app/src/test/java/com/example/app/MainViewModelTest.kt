package com.example.app

import com.example.cabifychallenge.presentation.viewmodel.GetProductsState
import com.example.cabifychallenge.presentation.viewmodel.MainActivityViewModel
import com.example.corenetwork.ApiResult
import com.example.domain.model.Products
import com.example.domain.usecase.GetProducts
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    private val getProducts: GetProducts = mockk()
    private val viewModel = MainActivityViewModel(getProducts)

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
        assertEquals(0, (viewModel.uiState.value as GetProductsState.GetProductsSuccess).products.products.size)
    }

    @Test
    fun `get products error should put ui state on error`(){
        coEvery { getProducts.invoke() } returns ApiResult.ERROR("Error")

        viewModel.getProducts()

        coVerify { getProducts.invoke() }
        assert(viewModel.uiState.value is GetProductsState.GetProductsError)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}