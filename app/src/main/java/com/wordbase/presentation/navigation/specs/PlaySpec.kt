package com.wordbase.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.gameplay.PlayScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object PlaySpec : IScreenSpec {
    override val route: String
        get() = "play"

    @Composable
    override fun Content(
        wordbaseViewModel: WordbaseViewModel,
        navController: NavHostController,
        context: Context
    ) {
        PlayScreen(wordbaseViewModel = wordbaseViewModel)
    }
}