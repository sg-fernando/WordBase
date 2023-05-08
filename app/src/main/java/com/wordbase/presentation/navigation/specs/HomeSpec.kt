package com.wordbase.presentation.navigation.specs

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.screens.HomeScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object HomeSpec : IScreenSpec {
    override val route: String
        get() = "home"

    @Composable
    override fun Content(
        wordbaseViewModel: WordbaseViewModel,
        navController: NavHostController,
        context: Context
    ) {
        wordbaseViewModel.getWordListItem("")
        HomeScreen(
            onPlayClick = {
                navController.navigate(route = PlaySpec.route)
            },
            onStoreClick = {
                navController.navigate(route = StoreSpec.route)
            },
            onWordListClick = {
                navController.navigate(route = WordListSpec.route)
            },
            onLeaderboardClick = {
                navController.navigate(route = LeaderboardSpec.route)
            },
            onSettingsClick = {
                navController.navigate(route = SettingsSpec.route)
            },
            onCoinsClick = {
                Toast.makeText(context,"Will be an easter egg after a certain number of clicks", Toast.LENGTH_SHORT).show()
            },
            onSoundClick = { b: Boolean -> wordbaseViewModel.playMuteSound(b) }
        )
    }
}