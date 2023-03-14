package com.wordbase.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.screens.SettingsScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object SettingsSpec : IScreenSpec {
    override val route: String
        get() = "settings"

    @Composable
    override fun Content(
        wordbaseViewModel: WordbaseViewModel,
        navController: NavHostController,
        context: Context
    ) {
        SettingsScreen(
            onBackClick = {
                navController.navigate(route = HomeSpec.route)
            }
        )
    }
}