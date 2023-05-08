package com.wordbase.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wordbase.data.Constants
import com.wordbase.data.WordListItem

@Composable
fun WordListDetail(
    wordListItem: WordListItem,
    onBackClick: () -> Unit
) {
    val buttonColors = ButtonDefaults.buttonColors(containerColor = Constants.white, contentColor = Constants.black)
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
                        WordListItem(
                            item = wordListItem,
                            onWordListClick = {}
                        )

                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(wordListItem.words) { word ->
                        Text(text = word)
                    }
                }
            }
        }
    }
}

@Composable
fun WordItem(item: WordListItem) {
    val buttonColors = ButtonDefaults.buttonColors(containerColor = Constants.white, contentColor = Constants.black)
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
            shape = RoundedCornerShape(Constants.borderRadius)
        ) {
            Box(modifier = Modifier.fillMaxSize(0.9f), contentAlignment = Alignment.Center) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = item.id.toString()+".")
                    Button(
                        onClick = { /*TODO*/ },
                        colors = buttonColors,
                        shape = RoundedCornerShape(Constants.borderRadius),
                        modifier = Modifier
                            .width(Constants.width)
                            .height(Constants.height * 1.5f)
                    ) {
                        Text(text = item.title, fontSize = Constants.buttonFontSize)
                    }
                    Text(text = "Proficiency: "+item.proficiency)
                    Text(text = "Amount of Words: "+item.words.size)
                }
            }
        }
    }
}