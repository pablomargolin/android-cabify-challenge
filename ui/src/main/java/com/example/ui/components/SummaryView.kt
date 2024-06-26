package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.Cart
import com.example.domain.model.Product
import com.example.ui.basics.CabifyButton
import com.example.ui.basics.CabifyText
import com.example.ui.foundation.Colors
import com.example.ui.foundation.styles.CabifyStyles

@Composable
fun SummaryView(
    cart: MutableState<Cart?>,
    onPay: (() -> Unit)
) {

    cart.value?.let { cart ->
        val priceWithoutDiscount = cart.products.sumOf { it.price?.toDouble() ?: 0.0 }.toFloat()
        val showDiscount = cart.totalPrice != priceWithoutDiscount

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubtotalRow(priceWithoutDiscount)
            Spacer(modifier = Modifier.height(5.dp))
            DeliveryRow()
            if (showDiscount){
                Spacer(modifier = Modifier.height(5.dp))
                DiscountRow(priceWithoutDiscount, cart.totalPrice)
            }
            Spacer(modifier = Modifier.height(5.dp))
            TotalRow(
                showDiscount,
                cart.totalPrice,
                priceWithoutDiscount
            )
            Spacer(modifier = Modifier.height(5.dp))
            CabifyButton(
                Modifier.fillMaxWidth(),
                style = CabifyStyles.buttonDefaultSmall,
                text = "Pay €${if(showDiscount) cart.totalPrice else priceWithoutDiscount}") {
                onPay()
            }
        }
    }
}

@Composable
private fun SubtotalRow(priceWithoutDiscount: Float) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        CabifyText(
            text = "Subtotal",
            typography = CabifyStyles.textTitleSmall,
            textColor = Color.Black)
        Spacer(modifier = Modifier.weight(1f))
        CabifyText(
            text = "€${priceWithoutDiscount}",
            typography = CabifyStyles.textTitleSmall,
            textColor = Color.Black)
    }
}
@Composable
private fun DeliveryRow(){
    Row(
        Modifier.fillMaxWidth()
    ) {
        CabifyText(
            text = "Delivery",
            typography = CabifyStyles.textTitleSmall,
            textColor = Color.Black)
        Spacer(modifier = Modifier.weight(1f))
        CabifyText(
            text = "€0.0",
            typography = CabifyStyles.textTitleSmall,
            textColor = Color.Black)
        Spacer(modifier = Modifier.width(2.dp))
        CabifyText(
            text = "€5.0",
            typography = CabifyStyles.textTitleSmallLineThrough,
            textColor = Color.Black)
    }
}
@Composable
private fun DiscountRow(priceWithoutDiscount: Float, totalPrice: Float) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        CabifyText(
            text = "Discount",
            typography = CabifyStyles.textTitleSmall,
            textColor = Color.Black)
        Spacer(modifier = Modifier.weight(1f))
        CabifyText(
            text = "€${priceWithoutDiscount - totalPrice}",
            typography = CabifyStyles.textTitleSmall,
            textColor = Color.Black)
    }
}
@Composable
private fun TotalRow(showDiscount: Boolean, totalPrice: Float, priceWithoutDiscount: Float) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        CabifyText(
            text = "Total",
            typography = CabifyStyles.textTitleSmall,
            textColor = Color.Black)
        Spacer(modifier = Modifier.weight(1f))
        CabifyText(
            text = "€${if (showDiscount) totalPrice else priceWithoutDiscount}",
            typography = CabifyStyles.textTitleSmall,
            textColor = if(showDiscount) Colors.ORANGE.value else Color.Black)
    }
}

@Preview
@Composable
fun PreviewSummaryView(){
    SummaryView(
        remember {
            mutableStateOf( Cart(mutableListOf(Product("VOUCHER", "voucher", 1000f)), 990f))
        }
    ) {}
}