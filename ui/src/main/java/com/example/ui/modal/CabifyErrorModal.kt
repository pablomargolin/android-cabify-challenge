package com.example.ui.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.basics.CabifyButton
import com.example.ui.basics.CabifyText
import com.example.ui.foundation.styles.CabifyStyles

@Composable
fun CabifyErrorModal(
    onPrimaryButton: (() -> Unit)
){
    val canOpenDialog = remember { mutableStateOf(true) }

    if (canOpenDialog.value){
        Dialog(onDismissRequest = {
            canOpenDialog.value = false
        },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )) {

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White
            ) {
                Column(
                    Modifier
                        .background(Color.White)
                        .padding(top = 24.dp, bottom = 24.dp)
                        .wrapContentSize(Alignment.Center)
                ) {
                    Column(
                        modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){

                        CabifyText(
                            text = "Error",
                            typography = CabifyStyles.textTitleMedium,
                            textColor = Color.Black)

                        Spacer(
                            modifier = Modifier.height(8.dp))

                        CabifyText(
                            text = "There was an error processing your request.",
                            typography = CabifyStyles.textTitleSmall,
                            textColor = Color.Gray,
                            textAlign = TextAlign.Center)

                        Spacer(modifier = Modifier.height(15.dp))

                        CabifyButton(
                            style = CabifyStyles.buttonDefaultSmall,
                            text = "Retry") {
                                onPrimaryButton()
                        }

                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCabifyErrorModal(){
    CabifyErrorModal {

    }
}