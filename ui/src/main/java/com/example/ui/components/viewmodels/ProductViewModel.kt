package com.example.ui.components.viewmodels

import androidx.compose.runtime.Composable
import com.example.domain.model.Product
import com.example.ui.components.ComponentListener
import com.example.ui.components.ProductView

class ProductViewModel(val product: Product): ComponentViewModel {
    @Composable
    override fun Build(componentListener: ComponentListener) {
        ProductView(product = product, componentListener)
    }

}