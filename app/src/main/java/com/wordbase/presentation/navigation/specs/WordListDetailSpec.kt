package com.wordbase.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.wordbase.presentation.screens.WordListDetail
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object WordListDetailSpec : IScreenSpec {
    override val route: String
        get() = "detail_wordlist/{wordListItemId}"

    @Composable
    override fun Content(
        wordbaseViewModel: WordbaseViewModel,
        navController: NavHostController,
        context: Context
    ) {
        val navBackStackEntry = navController.currentBackStackEntry
        val arguments = navBackStackEntry?.arguments
        if (arguments != null) {
            val wordListItemId = arguments.getString("wordListItemId")
            if (wordListItemId != null) {
                wordbaseViewModel.getWordListItem(wordListItemId)
            }
        }
        val currentWordListItemState = wordbaseViewModel.currentWordListState.collectAsState()
        currentWordListItemState.value?.let {
            WordListDetail(
                wordListItem = it,
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}