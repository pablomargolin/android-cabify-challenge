package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.foundation.styles.CabifyStyles
import com.example.ui.foundation.styles.CardStyle

@Composable
fun CabifyCard(
    modifier: Modifier = Modifier,
    style: CardStyle = CabifyStyles.cardDefault,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
) {
    val shape = RoundedCornerShape(style.shapeCornerRadius)
    Box(
        modifier = modifier
            .background(
                color = style.shapeColor,
                RoundedCornerShape(style.shapeCornerRadius)
            )
            .border(
                style.shapeBorderWidth,
                style.shapeBorderColor,
                RoundedCornerShape(style.shapeCornerRadius)
            )
            .clip(shape),
        contentAlignment = contentAlignment,
        content = content
    )
}

@Preview
@Composable
fun PreviewCabifyCard(){
    CabifyCard {
        CabifyText(modifier = Modifier.padding(20.dp), text = "Text!", typography = CabifyStyles.textTitleMedium, textColor = Color.Black)
    }
}