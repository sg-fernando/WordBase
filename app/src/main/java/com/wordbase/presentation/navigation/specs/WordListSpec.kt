package com.wordbase.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.screens.WordListScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object WordListSpec : IScreenSpec {
    override val route: String
        get() = "wordlist"

    @Composable
    override fun Content(
        wordbaseViewModel: WordbaseViewModel,
        navController: NavHostController,
        context: Context
    ) {
        WordListScreen(
            wordbaseViewModel = wordbaseViewModel,
            onBackClick = {
                navController.navigate(route = HomeSpec.route)
            },
            onMyListClick = {
                // nothing, already here
            },
            onCreateNewListClick = {
                navController.navigate("create_wordlist")
            },
            onPreMadeListsClick = {
                navController.navigate("pre_wordlist")
            },
            onWordListClick = {

            }
        )
    }
}