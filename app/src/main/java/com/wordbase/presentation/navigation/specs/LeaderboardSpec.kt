package com.wordbase.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.screens.LeaderboardScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object LeaderboardSpec : IScreenSpec {
    override val route: String
        get() = "leaderboard"

    @Composable
    override fun Content(
        wordbaseViewModel: WordbaseViewModel,
        navController: NavHostController,
        context: Context
    ) {
        LeaderboardScreen(
            wordbaseViewModel = wordbaseViewModel,
            onBackClick = {
                navController.navigate(route = HomeSpec.route)
            }
        )
    }
}