package com.wordbase.presentation.homescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScreenTemplate(onBackClick: () -> Unit, content: @Composable () -> Unit) {
    val whiteColor = Color(255, 255, 255, 255)
    val blackColor = Color(0, 0, 0, 255)
    val buttonColors = ButtonDefaults.buttonColors(containerColor = whiteColor, contentColor = blackColor)
    val height = 40.dp
    val width = 200.dp
    val rad = 25
    Column {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Row(Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = onBackClick,
                        colors = buttonColors,
                        shape = RoundedCornerShape(rad),
                        modifier = Modifier
                            .height(height)
                            .width(height)
                    ) {}
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back Button")
                }
            }
            Box(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 15.dp)
            ) {

                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(rad)
                ) {
                    Box(modifier = Modifier.fillMaxWidth(.95f).fillMaxHeight(.8f)) {
                        content()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TemplatePreview() {
    ScreenTemplate(onBackClick = { /*TODO*/ }) {

    }
}