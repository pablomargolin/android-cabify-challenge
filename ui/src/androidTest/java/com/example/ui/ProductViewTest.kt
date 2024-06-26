package com.example.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.domain.model.Product
import com.example.ui.components.ComponentListener
import com.example.ui.components.ProductView
import org.junit.Rule
import org.junit.Test

class ProductViewTest {
    val product = Product("voucher", "voucher", 5f)

    @get:Rule val rule = createComposeRule()

    val componentListener = object : ComponentListener {
        override fun productAdded(product: Product) {}
        override fun productRemoved(product: Product) {}
        override fun payCart() {}
    }

    @Test
    fun testProductView_tags(){
        rule.setContent{
            MaterialTheme{
                ProductView(
                    product = product,
                    componentListener)
            }
        }

        rule.onNodeWithTag("productImage").assertExists()
        rule.onNodeWithTag("productName").assertExists()
        rule.onNodeWithTag("productDescription").assertExists()
        rule.onNodeWithTag("productPrice").assertExists()

    }
}