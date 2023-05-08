package com.wordbase.presentation.viewmodel

import android.media.MediaPlayer
import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wordbase.data.LeaderboardItem
import com.wordbase.data.WordListItem
import com.wordbase.data.WordbaseRepo
import com.wordbase.presentation.gameplay.BatView
import com.wordbase.presentation.gameplay.GameViewType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WordbaseViewModel (
    private val mediaPlayer: MediaPlayer? = null,
    private val repo: WordbaseRepo
)  : ViewModel() {

    private val item2 = WordListItem(id=1, "Word List Title", 0.5, arrayListOf("test", "word"))
    val wordlists = arrayListOf(item2, item2, item2)

    private val mCurrentView = mutableStateOf<GameViewType>(GameViewType.SpellView)
    val currentView: State<GameViewType>
    get() = mCurrentView

    fun switchToPitcherView() {
        mCurrentView.value = GameViewType.PitcherView
    }
    fun switchToSpellView() {
        mCurrentView.value = GameViewType.SpellView
    }

    fun switchToBatView() {
        mCurrentView.value = GameViewType.BatView
    }

    fun getWordListItem(wordListItemId: Int): WordListItem {
        return wordlists[wordListItemId]
    }

    fun playMuteSound(boolean: Boolean) {
        if (boolean) {
            mediaPlayer?.start()
            println("start")
        } else {
            mediaPlayer?.pause()
        }
    }

    private val mLeaderboard: MutableStateFlow<List<LeaderboardItem>> =
        MutableStateFlow(emptyList())
    val leaderboardState: StateFlow<List<LeaderboardItem>>
        get() = mLeaderboard.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getLeaderboard().collect { leaderboard ->
                mLeaderboard.update { leaderboard }
            }
        }
    }
}