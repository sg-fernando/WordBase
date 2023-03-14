package com.wordbase.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wordbase.R
import com.wordbase.data.Constants

@Composable
fun HomeScreen(
    onPlayClick: () -> Unit,
    onStoreClick: () -> Unit,
    onWordListClick: () -> Unit,
    onLeaderboardClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onCoinsClick: () -> Unit,
) {
    val soundIcon = remember { mutableStateOf(R.drawable.baseline_volume_up_24) }
    val buttonColors = ButtonDefaults.buttonColors(containerColor = Constants.white, contentColor = Constants.black)
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
                        shape = RoundedCornerShape(Constants.borderRadius),
                        modifier = Modifier
                            .height(Constants.height)
                            .width(Constants.height)
                    ) {}
                    Image(painter = painterResource(id = R.drawable.baseline_leaderboard_24), contentDescription = stringResource(
                        id = R.string.leaderboard
                    ))
                }
                Spacer(Modifier.weight(0.025f))
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = onCoinsClick,
                        colors = buttonColors,
                        shape = RoundedCornerShape(Constants.borderRadius),
                        modifier = Modifier
                            .height(Constants.height)
                            .width(Constants.height * 2)
                    ) {}
                    Row() {
                        Image(painter = painterResource(id = R.drawable.baseline_monetization_on_24), contentDescription = stringResource(
                            id = R.string.coins
                        ))
                        Text(text = "1.24k")
                    }
                }
                Spacer(Modifier.weight(1f))
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = {
                              if (soundIcon.value == R.drawable.baseline_volume_up_24) {
                                  soundIcon.value = R.drawable.baseline_volume_off_24
                              } else {
                                  soundIcon.value = R.drawable.baseline_volume_up_24
                              }
                        },
                        colors = buttonColors,
                        shape = RoundedCornerShape(Constants.borderRadius),
                        modifier = Modifier
                            .height(Constants.height)
                            .width(Constants.height)
                    ) {}
                    Image(painter = painterResource(id = soundIcon.value), contentDescription = "Sound")
                }
                Spacer(Modifier.weight(0.025f))
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = onSettingsClick,
                        colors = buttonColors,
                        shape = RoundedCornerShape(Constants.borderRadius),
                        modifier = Modifier
                            .height(Constants.height)
                            .width(Constants.height)
                    ) {}
                    Icon(Icons.Filled.Settings, contentDescription = stringResource(id = R.string.settings))
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold,
                    color = Constants.white,
                    fontSize = Constants.titleFontSize
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = onPlayClick,
                    colors = buttonColors,
                    shape = RoundedCornerShape(Constants.borderRadius),
                    modifier = Modifier
                        .width(Constants.width)
                        .height(Constants.height)
                ) {
                    Text(stringResource(id = R.string.play), color = Constants.black, fontSize = Constants.buttonFontSize, fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    colors = buttonColors,
                    onClick = onStoreClick,
                    shape = RoundedCornerShape(Constants.borderRadius),
                    modifier = Modifier
                        .width(Constants.width)
                        .height(Constants.height)
                ) {
                    Text(stringResource(id = R.string.store), color = Constants.black, fontSize = Constants.buttonFontSize, fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    colors = buttonColors,
                    onClick = onWordListClick,
                    shape = RoundedCornerShape(Constants.borderRadius),
                    modifier = Modifier
                        .width(Constants.width)
                        .height(Constants.height)
                ) {
                    Text(stringResource(id = R.string.wordlists), color = Constants.black, fontSize = Constants.buttonFontSize, fontWeight = FontWeight.Medium)
                }
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen({}, {}, {}, {}, {}, {})
}