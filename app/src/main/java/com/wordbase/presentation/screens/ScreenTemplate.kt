package com.wordbase.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wordbase.data.Constants

@Composable
fun ScreenTemplate(onBackClick: () -> Unit, topBarContent: @Composable () -> Unit, content: @Composable () -> Unit) {
    val buttonColors = ButtonDefaults.buttonColors(containerColor = Constants.white, contentColor = Constants.black)
    Column {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(20.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = onBackClick,
                        colors = buttonColors,
                        shape = RoundedCornerShape(Constants.borderRadius),
                        modifier = Modifier
                            .height(Constants.height)
                            .width(Constants.height)
                    ) {}
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back Button")
                }
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        topBarContent()
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 15.dp)
            ) {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(Constants.borderRadius),
                    modifier = Modifier
                        .fillMaxWidth(.95f)
                        .fillMaxHeight(.8f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
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
    ScreenTemplate(onBackClick = { /*TODO*/ }, {}) {
        Text(text = "da")
    }
}