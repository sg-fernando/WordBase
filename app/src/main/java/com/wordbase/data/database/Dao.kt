package com.wordbase.data.database

import androidx.room.*
import androidx.room.Dao
import com.wordbase.data.LeaderboardItem
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLeaderboardItem(leaderboardItem: LeaderboardItem)

    @Query("SELECT * FROM leaderboard")
    fun getLeaderboardItem(): Flow<List<LeaderboardItem>>

}