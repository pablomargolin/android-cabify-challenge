package com.example.ui.presentation

import android.content.Intent
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.SecureFlagPolicy
import coil.compose.AsyncImage
import com.example.ui.presentation.complete.PaymentActivity
import com.example.ui.presentation.viewmodel.GetProductsState
import com.example.ui.presentation.viewmodel.MainActivityViewModel
import com.example.domain.model.Product
import com.example.ui.basics.CabifyButton
import com.example.ui.basics.CabifyText
import com.example.ui.components.ComponentListener
import com.example.ui.components.SummaryView
import com.example.ui.components.viewmodels.ComponentFactory
import com.example.ui.foundation.Colors
import com.example.ui.foundation.styles.CabifyStyles
import com.example.ui.modal.CabifyErrorModal
import com.example.ui.modal.CabifyProgressBar
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity(), ComponentListener {
    private val viewModel: MainActivityViewModel by viewModels()

    private val showSummaryModal = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SetUiStateObserver()
            viewModel.getProducts()
        }
    }

    @Composable
    private fun MainScreen() {
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
                DiscountImage(
                    text = "2x1 on Vouchers! Discounts on t-shirts!")
                Spacer(
                    modifier = Modifier.height(20.dp))
                ProductsScreen(viewModel.componentsFactory)
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
                    MainScreen()
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

        if (showSummaryModal.value){
            ModalBottomSheet(
                onDismissRequest = {
                    showSummaryModal.value = false
                },
                properties = ModalBottomSheetProperties(SecureFlagPolicy.SecureOff, isFocusable = true, shouldDismissOnBackPress = true)
            ) {
                SummaryView(cart = viewModel.cart) {
                    payCart()
                }
            }
        }
    }

    @Composable
    private fun ProductsScreen(componentsFactory: List<ComponentFactory>){
        Column {
            CabifyText(
                text = "For you",
                typography = CabifyStyles.textTitleBoldMedium,
                textColor = Color.Black)

            Spacer(modifier = Modifier.height(10.dp))
            
            Column(Modifier.fillMaxSize()) {
                LazyColumn(Modifier.weight(1f)) {
                    items(componentsFactory.size){ index ->

                        componentsFactory[index].Create(componentListener = this@MainActivity)

                        if (index != componentsFactory.lastIndex) {
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

                CabifyButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = CabifyStyles.buttonDefault,
                    text = "Pay",
                    enabled = viewModel.cart.value != null) {
                        showSummaryModal.value = true
                }
            }
        }
    }

    override fun productAdded(product: Product) {
        viewModel.productAdded(product)
    }

    override fun productRemoved(product: Product) {
        viewModel.productRemoved(product)
    }

    override fun payCart() {
        startActivity(Intent(this@MainActivity, PaymentActivity::class.java))
    }
}