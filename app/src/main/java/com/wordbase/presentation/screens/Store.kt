package com.wordbase.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.wordbase.R
import com.wordbase.data.Constants

@Composable
fun StoreScreen(onBackClick: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        // Centered content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f), contentAlignment = Alignment.Center
        ) {
            Background()
            ScreenTemplate(
                onBackClick = onBackClick,
                topBarContent = {
                    Text(
                        text = stringResource(id = R.string.store),
                        fontWeight = FontWeight.Bold,
                        color = Constants.white,
                        fontSize = Constants.subtitleFontSize
                    )
                }
            ) {

            }
        }
    }
}