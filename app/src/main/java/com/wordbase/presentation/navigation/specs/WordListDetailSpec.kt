package com.wordbase.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.screens.WordListDetail
import com.wordbase.presentation.screens.WordListScreen
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
        val wordListItemId = 0
        if (arguments != null) {
            val wordListItemId = arguments.getString("wordListItemId")
        }
        WordListDetail(
            wordbaseViewModel = wordbaseViewModel,
            wordListItem = wordbaseViewModel.getWordListItem(wordListItemId),
            onBackClick = {
                navController.navigate(route = WordListSpec.route)
            },
        )
    }
}