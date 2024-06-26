package com.example.ui.components.viewmodels

import androidx.compose.runtime.Composable
import com.example.domain.model.Product
import com.example.ui.components.ComponentListener
import com.example.ui.components.ProductView

class ProductFactory(val product: Product): ComponentFactory {
    @Composable
    override fun Create(componentListener: ComponentListener) {
        ProductView(
            product = product,
            componentListener)
    }

}