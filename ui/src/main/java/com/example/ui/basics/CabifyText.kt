package com.example.ui.basics

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ui.foundation.text.Typography

@Composable
fun CabifyText(
    modifier: Modifier = Modifier,
    text: String,
    typography: Typography,
    textColor: Color,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
){
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        fontFamily = typography.font,
        fontSize = typography.fontSize,
        textDecoration = typography.textDecoration,
        maxLines = maxLines,
        minLines = minLines
    )
}