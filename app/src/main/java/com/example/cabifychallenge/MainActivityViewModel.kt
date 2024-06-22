package com.example.cabifychallenge

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

    fun getProducts(){
        viewModelScope.launch {
            when (val products = getProducts.invoke()) {
                is ApiResult.SUCCESS -> {
                    products.result
                }
                is ApiResult.ERROR -> {
                    products.error
                }
            }
        }
    }
}