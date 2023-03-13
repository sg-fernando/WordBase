package com.wordbase.presentation.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.wordbase.R

@Composable
fun LeaderboardScreen(onBackClick: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        // Centered content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f), contentAlignment = Alignment.Center
        ) {
            Background()
            ScreenTemplate(onBackClick = onBackClick) {
                
            }
        }
    }
}