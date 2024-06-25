package com.example.cabifychallenge.presentation.complete

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.cabifychallenge.R
import com.example.cabifychallenge.presentation.MainActivity
import com.example.cabifychallenge.ui.theme.CabifyChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CabifyChallengeTheme {
                ShowPaymentAnimation()
            }
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