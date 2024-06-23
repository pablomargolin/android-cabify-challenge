package com.example.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.domain.model.Product
import com.example.ui.components.ProductView
import org.junit.Rule
import org.junit.Test

class ProductViewTest {
    val product = Product("voucher", "voucher", 5f)

    @get:Rule val rule = createComposeRule()

    @Test
    fun testProductView_tags(){
        rule.setContent{
            MaterialTheme{
                ProductView(product = product)
            }
        }

        rule.onNodeWithTag("productImage").assertExists()
        rule.onNodeWithTag("productName").assertExists()
        rule.onNodeWithTag("productDescription").assertExists()
        rule.onNodeWithTag("productPrice").assertExists()

    }
}