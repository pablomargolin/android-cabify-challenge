package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.model.Product
import com.example.ui.basics.CabifyButton
import com.example.ui.basics.CabifyText
import com.example.ui.foundation.styles.CabifyStyles

@Composable
fun ProductView(
    product: Product,
    componentListener: ComponentListener
){

    val countProducts = remember {
        mutableIntStateOf(0)
    }
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        AsyncImage(
            modifier = Modifier
                .height(100.dp)
                .testTag("productImage"),
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Fit)

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            CabifyText(
                modifier = Modifier.testTag("productName"),
                text = product.name ?: "",
                typography = CabifyStyles.textTitleMedium,
                textColor = Color.Black)

            CabifyText(
                modifier = Modifier.testTag("productDescription"),
                text = "Product description",
                typography = CabifyStyles.textTitleSmall,
                textColor = Color.Gray)

            CabifyText(
                modifier = Modifier.testTag("productPrice"),
                text = "$ ${product.price}",
                typography = CabifyStyles.textTitleBoldMedium,
                textColor = Color.Black)
        }

        Spacer(modifier = Modifier
            .weight(1f)
            .fillMaxHeight())

        Column {
            CabifyButton(
                style = CabifyStyles.buttonDefaultSmall, text = "Add") {
                componentListener.productAdded(product)
                countProducts.intValue += 1
            }
            AnimatedVisibility(visible = countProducts.intValue > 0) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    CabifyText(text = countProducts.intValue.toString(), typography = CabifyStyles.textTitleMedium, textColor = Color.Black)
                    Spacer(modifier = Modifier.width(5.dp))
                    CabifyButton(
                        style = CabifyStyles.buttonDefaultSmallRed, text = "X") {
                        componentListener.productRemoved(product)
                        countProducts.intValue -= 1
                    }
                }
            }

        }
   }
}

@Preview
@Composable
fun PreviewProductView(){
    ProductView(product = Product("VOUCHER", "CabifyVoucher", 5f), object : ComponentListener {
        override fun productAdded(product: Product) {}
        override fun productRemoved(product: Product) {}
        override fun payCart() {}
    })
}

val image = "https://i.imgur.com/hFw1u48.png"