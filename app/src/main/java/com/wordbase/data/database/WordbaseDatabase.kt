package com.wordbase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wordbase.data.LeaderboardItem
import com.wordbase.data.WordListItem


@Database(entities = [LeaderboardItem::class, WordListItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class WordbaseDatabase: RoomDatabase() {
    companion object {
        @Volatile private var INSTANCE: WordbaseDatabase? = null
        fun getInstance(context: Context): WordbaseDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context, WordbaseDatabase::class.java,
                        "wordbase-database").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
    abstract val dao: Dao
}

