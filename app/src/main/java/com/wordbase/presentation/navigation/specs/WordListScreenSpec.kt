package com.wordbase.presentation.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.homescreen.WordListScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object WordListScreenSpec : IScreenSpec {
    override val route: String
        get() = "wordlist"

    @Composable
    override fun Content(wordbaseViewModel: WordbaseViewModel, navController: NavHostController) {
        WordListScreen(
            onBackClick = {
                navController.navigate(route = HomeScreenSpec.route)
            }
        )
    }
}