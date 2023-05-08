package com.wordbase.presentation.navigation.specs

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.wordbase.presentation.screens.PreWordListScreen
import com.wordbase.presentation.viewmodel.WordbaseViewModel

object PreWordListSpec : IScreenSpec {
    override val route: String
        get() = "pre_wordlist"

    @Composable
    override fun Content(
        wordbaseViewModel: WordbaseViewModel,
        navController: NavHostController,
        context: Context
    ) {
        val wordListState = wordbaseViewModel.wordListState.collectAsState()
        PreWordListScreen(
            wordlists = wordListState.value,
            onBackClick = {
                navController.navigate(route = HomeSpec.route)
            },
            onMyListClick = {
                navController.navigate("wordlist")
            },
            onCreateNewListClick = {
                navController.navigate("create_wordlist")
            },
            onPreMadeListsClick = {
                // nothing, already here
            },
            onWordListClick = { wordListItemId ->
                navController.navigate(route = "detail_wordlist/$wordListItemId")
            },
            onAddClick = { item ->
                val updatedWordListItem = item.copy(added = 1)
                wordbaseViewModel.updateWordListItem(updatedWordListItem)
            }
        )
    }
}