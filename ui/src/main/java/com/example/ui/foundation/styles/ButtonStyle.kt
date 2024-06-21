package com.example.ui.foundation.styles

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.foundation.text.Typography

data class ButtonStyle(
    val color: Color,
    val shape: Shape,
    val typography: Typography,
    val elevation: Dp = 0.dp
)
