package com.example.ui.foundation.text

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

data class Typography(
    val font: FontFamily,
    val fontSize: TextUnit,
    val textDecoration: TextDecoration
)
