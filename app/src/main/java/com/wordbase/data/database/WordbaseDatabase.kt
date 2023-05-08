package com.wordbase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wordbase.data.LeaderboardItem


@Database(entities = [LeaderboardItem :: class], version = 1)
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

