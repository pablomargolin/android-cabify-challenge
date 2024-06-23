package com.example.cabifychallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cabifychallenge.presentation.viewmodel.GetProductsState
import com.example.cabifychallenge.presentation.viewmodel.MainActivityViewModel
import com.example.cabifychallenge.ui.theme.CabifyChallengeTheme
import com.example.domain.model.Products
import com.example.ui.basics.CabifyText
import com.example.ui.components.ProductView
import com.example.ui.foundation.Colors
import com.example.ui.foundation.styles.CabifyStyles
import com.example.ui.modal.CabifyErrorModal
import com.example.ui.modal.CabifyProgressBar
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CabifyChallengeTheme {
                SetUiStateObserver()
                viewModel.getProducts()
            }
        }
    }

    @Composable
    private fun MainScreen(products: Products) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                    CabifyText(
                        text = "Pablo Margolin",
                        typography = CabifyStyles.textTitleSmall,
                        textColor = Color.Black)

                        Spacer(modifier = Modifier.weight(1f))

                        AsyncImage(
                            model = "https://cdn-icons-png.freepik.com/512/9203/9203764.png",
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .padding(end = 10.dp))
                    }
                })
            }
        ) { padding ->
            Column (
                Modifier
                    .padding(padding)
                    .padding(start = 10.dp, end = 10.dp)) {
                DiscountImage(text = "2x1 on Vouchers! Discounts on t-shirts!")
                
                Spacer(modifier = Modifier.height(20.dp))
                
                ProductsScreen(products = products)
            }
        }
    }

    @Composable
    fun DiscountImage(
        modifier: Modifier = Modifier,
        text: String
    ) {
        val shape =  RoundedCornerShape(8.dp)
        val height = 150.dp
        Box(
            modifier = modifier
                .height(height)
                .fillMaxWidth()
                .background(Color.White, shape = shape),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                modifier = Modifier.clip(shape),
                model = "https://blog.nu.com.mx/wp-content/uploads/2023/07/Nu_Descuentos_HEADER.jpg",
                contentDescription = null,
                contentScale = ContentScale.FillWidth)

            CabifyText(
                modifier = Modifier.padding(10.dp),
                text = text,
                typography =
                CabifyStyles.textTitleBoldMedium,
                textColor = Color.White)

        }
    }


    @Composable
    private fun SetUiStateObserver(){
        viewModel.uiState.value.let { uiState ->
            when(uiState){
                is GetProductsState.GetProductsSuccess -> {
                    MainScreen(uiState.products)
                }
                is GetProductsState.GetProductsError -> {
                    CabifyErrorModal {
                        viewModel.getProducts()
                    }
                }
                null -> {
                    CabifyProgressBar()
                }
            }
        }
    }

    @Composable
    private fun ProductsScreen(products: Products){
        Column {
            CabifyText(
                text = "For you",
                typography = CabifyStyles.textTitleBoldMedium,
                textColor = Color.Black)

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                Modifier.fillMaxSize()
            ) {
                items(products.products.size){ index ->

                    ProductView(product = products.products[index])

                    if (index != products.products.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                                .height(1.dp),
                            color = Colors.GREY.value,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}