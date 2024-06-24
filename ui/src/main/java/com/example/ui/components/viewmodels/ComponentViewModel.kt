package com.example.ui.components.viewmodels

import androidx.compose.runtime.Composable
import com.example.ui.components.ComponentListener

interface ComponentViewModel {
    @Composable
    fun Build(componentListener: ComponentListener)
}