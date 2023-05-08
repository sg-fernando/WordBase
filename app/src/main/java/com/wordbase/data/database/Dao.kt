package com.wordbase.data.database

import androidx.room.*
import androidx.room.Dao
import com.wordbase.data.LeaderboardItem
import com.wordbase.data.WordListItem
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLeaderboardItem(leaderboardItem: LeaderboardItem)
    @Query("SELECT * FROM leaderboard")
    fun getLeaderboard(): Flow<List<LeaderboardItem>>

    @Query("SELECT * FROM wordlist WHERE added = 1")
    fun getInstalledWordList(): Flow<List<WordListItem>>

    @Query("SELECT * FROM wordlist WHERE added = 0")
    fun getNotInstalledWordList(): Flow<List<WordListItem>>

    @Query("SELECT * FROM wordlist")
    fun getAllWordList(): Flow<List<WordListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWordListItem(wordListItem: WordListItem)

    @Query("SELECT * FROM wordlist WHERE id = :id")
    suspend fun getWordListById(id: String): WordListItem?

    @Update
    suspend fun updateWordListItem(wordListItem: WordListItem)
}