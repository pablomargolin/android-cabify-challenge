package com.example.ui.basics

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.foundation.styles.ButtonStyle
import com.example.ui.foundation.styles.CabifyStyles

@Composable
fun CabifyButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyle,
    text: String,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = if (enabled) style.color else Color.LightGray),
        shape = style.shape,
        modifier = modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)

    ) {
        CabifyText(text = text, typography = style.typography, textColor = Color.White)
    }
}

@Preview
@Composable
fun PreviewCabifyButton(){
    CabifyButton(style = CabifyStyles.buttonDefault, text = "Buy now") {}
}