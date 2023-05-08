package com.wordbase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaderboard")
data class LeaderboardItem (
    @PrimaryKey val id: Int,
    val dateString: String,
    val runs: Int,
    val outs: Int,
    val homeruns: Int
)