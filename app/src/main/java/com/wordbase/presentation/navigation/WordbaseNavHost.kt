package com.wordbase.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import com.wordbase.presentation.navigation.specs.IScreenSpec
import com.wordbase.presentation.viewmodel.WordbaseViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

@Composable
fun WordbaseNavHost(navController: NavHostController, wordbaseViewModel: WordbaseViewModel, context: Context) {
    NavHost(
        navController = navController,
        startDestination = IScreenSpec.root
    ) {
        navigation(
            route = IScreenSpec.root,
            startDestination = IScreenSpec.startDestination
        ) {
            IScreenSpec.allScreens.forEach { screen ->
                if(screen != null) {
                    composable(
                        route = screen.route,
                        ) {
                        screen.Content(wordbaseViewModel = wordbaseViewModel, navController = navController, context = context)
                    }
                }
            }
        }
    }
}