package com.wordbase.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wordbase.R
import com.wordbase.data.Constants
import com.wordbase.presentation.viewmodel.WordbaseViewModel

@Composable
fun CreateWordListScreen(
    wordbaseViewModel: WordbaseViewModel,
    onBackClick: () -> Unit,
    onMyListClick: () -> Unit,
    onCreateNewListClick: () -> Unit,
    onPreMadeListsClick: () -> Unit
) {
    val buttonColors = ButtonDefaults.buttonColors(containerColor = Constants.white, contentColor = Constants.black)
    val words = remember { mutableStateOf("") }
    val title = remember { mutableStateOf("") }
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        WordListTopBar(
                            onMyListClick = onMyListClick,
                            onCreateNewListClick = onCreateNewListClick,
                            onPreMadeListsClick = onPreMadeListsClick,
                            disabled = 2
                        )
                    }
                }
            ) {
                Column() {
                    Row() {
                        Text(text = stringResource(id = R.string.list_title))
                        TextField(value = title.value, onValueChange = { newTitle -> title.value = newTitle })
                    }
                    Row() {
                        Column() {
                            Text(text = stringResource(id = R.string.words_here))
                            TextField(
                                value = words.value,
                                onValueChange = { newWords -> words.value = newWords },
                                singleLine = false,
                                modifier = Modifier
                                    .fillMaxWidth(.9f)
                                    .fillMaxHeight(.5f)
                            )
                        }
                    }
                }
            }
        }
    }
}