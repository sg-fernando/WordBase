package com.wordbase.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wordbase.presentation.screens.StoreScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object StoreSpec : IScreenSpec {
    override val route: String
        get() = "store"

    @Composable
    override fun Content(
        wordbaseViewModel: WordbaseViewModel,
        navController: NavHostController,
        context: Context
    ) {
        StoreScreen(
            onBackClick = {
                navController.navigate(route = HomeSpec.route)
            }
        )
    }
}