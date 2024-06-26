package com.example.ui.presentation.complete

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ui.R
import com.example.ui.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowPaymentAnimation()
        }
    }

    @Composable
    private fun ShowPaymentAnimation(){
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.tick_purchase))
        val progress by animateLottieCompositionAsState(composition = composition)

        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition)

        if (progress == 1.0f){
            startActivity(Intent(this@PaymentActivity, MainActivity::class.java))
            finish()
        }
    }
}