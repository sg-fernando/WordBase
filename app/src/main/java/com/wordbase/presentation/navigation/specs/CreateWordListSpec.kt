package com.wordbase.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.screens.CreateWordListScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object CreateWordListSpec : IScreenSpec {
    override val route: String
        get() = "create_wordlist"

    @Composable
    override fun Content(
        wordbaseViewModel: WordbaseViewModel,
        navController: NavHostController,
        context: Context
    ) {
        CreateWordListScreen(
            wordbaseViewModel = wordbaseViewModel,
            onBackClick = {
                navController.navigate(route = HomeSpec.route)
            },
            onMyListClick = {
                navController.navigate("wordlist")
            },
            onCreateNewListClick = {
                // nothing, already here
            },
            onPreMadeListsClick = {
                navController.navigate("pre_wordlist")
            }
        )
    }
}