package com.wordbase.presentation.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wordbase.R

@Composable
fun HomeScreen(
    onPlayClick: () -> Unit,
    onStoreClick: () -> Unit,
    onWordListClick: () -> Unit,
    onLeaderboardClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    val whiteColor = Color(255, 255, 255, 255)
    val blackColor = Color(0, 0, 0, 255)
    val buttonColors = ButtonDefaults.buttonColors(containerColor = whiteColor, contentColor = blackColor)
    val rad = 25
    val height = 40.dp
    val width = 200.dp
    val buttonTextSize = 18.sp
    Column(Modifier.fillMaxSize()) {
        // Centered content
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Background()
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(20.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = onLeaderboardClick,
                        colors = buttonColors,
                        shape = RoundedCornerShape(rad),
                        modifier = Modifier
                            .height(height)
                            .width(height)
                    ) {}
                    Image(painter = painterResource(id = R.drawable.baseline_leaderboard_24), contentDescription = "Leaderboard")
                }
                Spacer(Modifier.weight(0.025f))
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = { /* handle sound button click */ },
                        colors = buttonColors,
                        shape = RoundedCornerShape(rad),
                        modifier = Modifier
                            .height(height)
                            .width(height * 2)
                    ) {}
                    Row() {
                        Image(painter = painterResource(id = R.drawable.baseline_monetization_on_24), contentDescription = "Coins")
                        Text(text = "1.24k")
                    }
                }
                Spacer(Modifier.weight(1f))
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = { /* handle sound button click */ },
                        colors = buttonColors,
                        shape = RoundedCornerShape(rad),
                        modifier = Modifier
                            .height(height)
                            .width(height)
                    ) {}
                    Image(painter = painterResource(id = R.drawable.baseline_volume_up_24), contentDescription = "Sound")
                }
                Spacer(Modifier.weight(0.025f))
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = onSettingsClick,
                        colors = buttonColors,
                        shape = RoundedCornerShape(rad),
                        modifier = Modifier
                            .height(height)
                            .width(height)
                    ) {}
                    Icon(Icons.Filled.Settings, contentDescription = "Settings")
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold,
                    color = whiteColor,
                    fontSize = 60.sp
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = onPlayClick,
                    colors = buttonColors,
                    shape = RoundedCornerShape(rad),
                    modifier = Modifier
                        .width(width)
                        .height(height)
                ) {
                    Text(stringResource(id = R.string.play), color = blackColor, fontSize = buttonTextSize, fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    colors = buttonColors,
                    onClick = onStoreClick,
                    shape = RoundedCornerShape(rad),
                    modifier = Modifier
                        .width(width)
                        .height(height)
                ) {
                    Text(stringResource(id = R.string.store), color = blackColor, fontSize = buttonTextSize, fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    colors = buttonColors,
                    onClick = onWordListClick,
                    shape = RoundedCornerShape(rad),
                    modifier = Modifier
                        .width(width)
                        .height(height)
                ) {
                    Text(stringResource(id = R.string.wordlists), color = blackColor, fontSize = buttonTextSize, fontWeight = FontWeight.Medium)
                }
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen({}, {}, {}, {}, {})
}