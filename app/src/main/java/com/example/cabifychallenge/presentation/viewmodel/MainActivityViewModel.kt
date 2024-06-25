package com.example.cabifychallenge.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.corenetwork.ApiResult
import com.example.domain.CartRepository
import com.example.domain.model.Cart
import com.example.domain.model.Product
import com.example.domain.model.Products
import com.example.domain.usecase.GetProducts
import com.example.ui.components.viewmodels.ComponentViewModel
import com.example.ui.components.viewmodels.ProductViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getProducts: GetProducts,
    private val cartRepository: CartRepository
): ViewModel() {
    val cart: MutableState<Cart?> = mutableStateOf(null)
    val uiState: MutableState<GetProductsState?> = mutableStateOf(null)
    val componentsViewModel = mutableListOf<ComponentViewModel>()

    fun getProducts(){
        uiState.value = null

        viewModelScope.launch {
            when (val products = getProducts.invoke()) {
                is ApiResult.SUCCESS -> {
                    setProductsComponentsViewModel(products.result)
                }
                is ApiResult.ERROR -> {
                    uiState.value = GetProductsState.GetProductsError
                }
            }
        }
    }

    private fun setProductsComponentsViewModel(products: Products){
        products.products.forEach {
            componentsViewModel.add(ProductViewModel(it))
        }

        uiState.value = GetProductsState.GetProductsSuccess
    }

    fun productAdded(product: Product) {
        cartRepository.addProduct(product)
        cart.value = cartRepository.getCart()
    }
}