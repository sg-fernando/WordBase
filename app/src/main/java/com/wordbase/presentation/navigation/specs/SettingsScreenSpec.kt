package com.wordbase.presentation.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.homescreen.SettingsScreen
import com.wordbase.presentation.homescreen.StoreScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object SettingsScreenSpec : IScreenSpec {
    override val route: String
        get() = "store"

    @Composable
    override fun Content(wordbaseViewModel: WordbaseViewModel, navController: NavHostController) {
        SettingsScreen(
            onBackClick = {
                navController.navigate(route = HomeScreenSpec.route)
            }
        )
    }
}