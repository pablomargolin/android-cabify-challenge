package com.example.ui.modal

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CabifyProgressBar(){
    Dialog(onDismissRequest = {}, properties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false
    )) {

        CircularProgressIndicator(
            color = Color.LightGray,
            modifier = Modifier.then(Modifier.size(60.dp))
        )
    }
}