package com.wordbase.presentation.viewmodel

import android.media.MediaPlayer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wordbase.data.LeaderboardItem
import com.wordbase.data.WordListItem
import com.wordbase.data.WordbaseRepo
import com.wordbase.presentation.gameplay.GameViewType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID

class WordbaseViewModel (
    private val mediaPlayer: MediaPlayer? = null,
    private val repo: WordbaseRepo
)  : ViewModel() {

    private val item = WordListItem(
        id = "2",
        title = "Word List Title",
        proficiency = 0.5,
        words = listOf("test", "word", "hello", "world"),
        grades = listOf(0,12),
        added = 1
    )

    private val mCurrentView = mutableStateOf<GameViewType>(GameViewType.BatView)
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

    fun getWordListItem(id: String) {
        mCurrentWordListIdState.update { id }
    }

    fun updateWordListItem(wordListItem: WordListItem) {
        repo.updateWordListItem(wordListItem)
    }

    fun createWordList(name: String, words: String) {
//        split words by comma and remove spaces on ends
        val words = words.split(",").map { it.trim() }
        val wordListItem = WordListItem(
            id = UUID.randomUUID().toString(),
            title = name,
            proficiency = 0.0,
            words = words,
            grades = listOf(0,12),
            added = 1
        )
        repo.addWordList(wordListItem)
    }

    fun playMuteSound(boolean: Boolean) {
        if (boolean) {
            mediaPlayer?.start()
            println("start")
        } else {
            mediaPlayer?.pause()
        }
        repo.addWordList(item)
    }

    private val mLeaderboard: MutableStateFlow<List<LeaderboardItem>> =
        MutableStateFlow(emptyList())
    val leaderboardState: StateFlow<List<LeaderboardItem>>
        get() = mLeaderboard.asStateFlow()

    private val mInstalledWordList: MutableStateFlow<List<WordListItem>> =
        MutableStateFlow(emptyList())
    val installedWordListState: StateFlow<List<WordListItem>>
        get() = mInstalledWordList.asStateFlow()

    private val mWordList: MutableStateFlow<List<WordListItem>> =
        MutableStateFlow(emptyList())
    val wordListState: StateFlow<List<WordListItem>>
        get() = mWordList.asStateFlow()

    private val mCurrentWordListIdState: MutableStateFlow<String> =
        MutableStateFlow("")
    private val mCurrentWordListState: MutableStateFlow<WordListItem?> = MutableStateFlow(null)

    val currentWordListState: StateFlow<WordListItem?>
        get() = mCurrentWordListState.asStateFlow()
    init {
        viewModelScope.launch {
            repo.getLeaderboard().collect { leaderboard ->
                mLeaderboard.update { leaderboard }
            }
        }
        viewModelScope.launch {
            repo.getInstalledWordList().collect { installedWordList ->
                mInstalledWordList.update { installedWordList }
            }
        }
        viewModelScope.launch {
            repo.getWordList().collect { wordList ->
                mWordList.update { wordList }
            }
        }
        viewModelScope.launch {
            mCurrentWordListIdState
                .map { id -> repo.getWordListById(id) }
                .collect { movie -> mCurrentWordListState.update { movie } }
        }
    }
}