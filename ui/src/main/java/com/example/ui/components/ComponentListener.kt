package com.example.ui.components

import com.example.domain.model.Product

interface ComponentListener {
    fun productAdded(product: Product)
    fun productRemoved(product: Product)
}