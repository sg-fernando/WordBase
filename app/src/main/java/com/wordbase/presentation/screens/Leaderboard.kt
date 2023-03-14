package com.wordbase.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wordbase.R
import com.wordbase.data.Constants
import com.wordbase.data.LeaderboardItem
import com.wordbase.presentation.viewmodel.WordbaseViewModel

@Composable
fun LeaderboardScreen(wordbaseViewModel: WordbaseViewModel, onBackClick: () -> Unit) {
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
                            text = stringResource(id = R.string.leaderboard),
                            fontWeight = FontWeight.Bold,
                            color = Constants.white,
                            fontSize = Constants.subtitleFontSize
                        )
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(wordbaseViewModel.leaderboard) { item ->
                        LeaderboardListItem(item = item)
                    }
                }
            }
        }
    }
}

@Composable
fun LeaderboardListItem(item: LeaderboardItem) {
    val rad = 25
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .height(100.dp)
                .padding(vertical = 10.dp),
            color = Constants.gray,
            shape = RoundedCornerShape(rad)
        ) {
            Box(modifier = Modifier.fillMaxSize(0.9f), contentAlignment = Alignment.Center) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = item.id.toString()+".")
                    Text(text = item.dateString)
                    Text(text = "Runs: "+item.runs)
                    Text(text = "Outs: "+item.outs)
                    Text(text = "Homeruns: "+item.runs)
                }
            }
        }
    }
}

@Preview
@Composable
fun LeaderboardPreview() {
    val m = WordbaseViewModel()
    LeaderboardScreen(wordbaseViewModel = m) {
        
    }
}

@Preview
@Composable
fun LeaderboardListItemPreview() {
    val m = WordbaseViewModel()
    LeaderboardListItem(item = m.leaderboard[0])
}