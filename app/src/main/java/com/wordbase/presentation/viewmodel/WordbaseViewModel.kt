package com.wordbase.presentation.viewmodel

import com.wordbase.data.LeaderboardItem
import com.wordbase.data.WordListItem

class WordbaseViewModel {

    private val item1 = LeaderboardItem(id = 1, dateString = "Date", runs = 1, outs = 1, homeruns = 1)
    val leaderboard = arrayListOf(item1, item1, item1, item1)

    private val item2 = WordListItem(id=1, "Word List Title", 0.5, arrayListOf("test", "word"))
    val wordlists = arrayListOf(item2, item2, item2)


    fun getWordListItem(wordListItemId: Int): WordListItem {
        return wordlists[wordListItemId]
    }
}