package com.wordbase.presentation.viewmodel

import android.media.MediaPlayer
import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.wordbase.data.LeaderboardItem
import com.wordbase.data.WordListItem
import com.wordbase.presentation.gameplay.BatView
import com.wordbase.presentation.gameplay.GameViewType

class WordbaseViewModel (
    private val mediaPlayer: MediaPlayer? = null
        ) {

    private val item1 = LeaderboardItem(id = 1, dateString = "Date", runs = 1, outs = 1, homeruns = 1)
    val leaderboard = arrayListOf(item1, item1, item1, item1)

    private val item2 = WordListItem(id=1, "Word List Title", 0.5, arrayListOf("test", "word"))
    val wordlists = arrayListOf(item2, item2, item2)

    private val mCurrentView = mutableStateOf<GameViewType>(GameViewType.BatView)
    val currentView: State<GameViewType>
    get() = mCurrentView

    fun switchToPitcherView() {
        mCurrentView.value = GameViewType.PitcherView
    }
    fun switchToSpellView() {
        mCurrentView.value = GameViewType.SpellView
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
}