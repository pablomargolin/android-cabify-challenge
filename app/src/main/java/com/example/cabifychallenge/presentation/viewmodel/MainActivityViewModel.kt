package com.example.cabifychallenge.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.corenetwork.ApiResult
import com.example.domain.usecase.GetProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getProducts: GetProducts
): ViewModel() {

    val uiState: MutableState<GetProductsState?> = mutableStateOf(null)

    fun getProducts(){
        uiState.value = null

        viewModelScope.launch {
            when (val products = getProducts.invoke()) {
                is ApiResult.SUCCESS -> {
                    uiState.value = GetProductsState.GetProductsSuccess(products = products.result)
                }
                is ApiResult.ERROR -> {
                    uiState.value = GetProductsState.GetProductsError
                }
            }
        }
    }
}