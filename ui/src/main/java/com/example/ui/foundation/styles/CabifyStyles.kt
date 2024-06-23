package com.example.ui.foundation.styles

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.R
import com.example.ui.foundation.Colors
import com.example.ui.foundation.text.Typography

object CabifyStyles {
    val textTitleMedium = Typography(FontFamily(Font(R.font.segma_medium)), 16.sp,TextDecoration.None)
    val textTitleBoldMedium = Typography(FontFamily(Font(R.font.segma_bold)), 16.sp,TextDecoration.None)
    val textTitleLarge = Typography(FontFamily(Font(R.font.segma_medium)), 20.sp,TextDecoration.None)
    val textTitleSmall = Typography(FontFamily(Font(R.font.segma_medium)), 12.sp,TextDecoration.None)

    val buttonDefault = ButtonStyle(
        Colors.GREEN.value,
        RoundedCornerShape(8.dp),
        textTitleMedium
    )

    val buttonDefaultSmall = ButtonStyle(
        Colors.GREEN.value,
        RoundedCornerShape(8.dp),
        textTitleSmall
    )

    val cardDefault = CardStyle(
        4.dp,
        1.dp,
        Color(0xFFDBDADE),
        Color.White
    )

    val cardDefaultWhite = CardStyle(
        4.dp,
        1.dp,
        Color.White,
        Color.White
    )
}