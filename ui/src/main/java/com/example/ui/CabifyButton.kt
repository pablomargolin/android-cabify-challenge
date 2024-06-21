package com.example.ui

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.foundation.styles.ButtonStyle
import com.example.ui.foundation.styles.CabifyStyles

@Composable
fun CabifyButton(
    modifier: Modifier = Modifier,
    style: ButtonStyle,
    text: String,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = style.color),
        shape = style.shape,
        modifier = modifier
    ) {
        CabifyText(text = text, typography = style.typography, textColor = Color.White)
    }
}

@Preview
@Composable
fun PreviewCabifyButton(){
    CabifyButton(style = CabifyStyles.buttonDefault, text = "Buy now") {}
}