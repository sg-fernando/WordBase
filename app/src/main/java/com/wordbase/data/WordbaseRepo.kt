package com.wordbase.data

import android.content.Context
import com.wordbase.data.database.Dao
import com.wordbase.data.database.WordbaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WordbaseRepo
private constructor(private val dao: Dao,
                    private val coroutineScope: CoroutineScope = GlobalScope
) {
    companion object {
        private var INSTANCE: WordbaseRepo? = null

        /**
         * @param context
         */
        fun getInstance(context: Context): WordbaseRepo {
            var instance = INSTANCE
            if(instance == null) {
                val database = WordbaseDatabase.getInstance(context)
                instance = WordbaseRepo(database.dao)
                INSTANCE = instance
            }
            return instance
        }
    }

    fun getLeaderboard(): Flow<List<LeaderboardItem>> =  dao.getLeaderboard()
    fun getInstalledWordList(): Flow<List<WordListItem>> =  dao.getInstalledWordList()
    fun getWordList(): Flow<List<WordListItem>> =  dao.getNotInstalledWordList()

    fun updateWordListItem(wordList: WordListItem) {
        coroutineScope.launch {
            dao.updateWordListItem(wordList)
        }
    }
    suspend fun getWordListById(id: String): WordListItem? = dao.getWordListById(id)

    fun addWordList(wordList: WordListItem){
        coroutineScope.launch{
            dao.addWordListItem(wordList)
        }
    }
}