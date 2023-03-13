package com.wordbase.presentation.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.homescreen.StoreScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object StoreScreenSpec : IScreenSpec {
    override val route: String
        get() = "store"

    @Composable
    override fun Content(wordbaseViewModel: WordbaseViewModel, navController: NavHostController) {
        StoreScreen(
            onBackClick = {
                navController.navigate(route = HomeScreenSpec.route)
            }
        )
    }
}