package com.wordbase.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.wordbase.data.database.Converters

@Entity(tableName = "wordlist")
@TypeConverters(Converters::class)
data class WordListItem (
    @PrimaryKey val id: String,
    val title: String,
    val proficiency: Double = 0.0,
    val words: List<String>,
    val grades: List<Int>,
    val added: Int = 0
)