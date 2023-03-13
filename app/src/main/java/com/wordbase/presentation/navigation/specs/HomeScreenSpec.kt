package com.wordbase.presentation.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.homescreen.HomeScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object HomeScreenSpec : IScreenSpec {
    override val route: String
        get() = "home"

    @Composable
    override fun Content(wordbaseViewModel: WordbaseViewModel, navController: NavHostController) {
        HomeScreen(
            onPlayClick = {

            },
            onStoreClick = {
                navController.navigate(route = StoreScreenSpec.route)
            },
            onWordListClick = {
                navController.navigate(route = WordListScreenSpec.route)
            },
            onLeaderboardClick = {
                navController.navigate(route = LeaderboardScreenSpec.route)
            },
            onSettingsClick = {
                navController.navigate(route = LeaderboardScreenSpec.route)
            }
        )
    }
}