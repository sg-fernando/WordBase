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


//    init {
//        Log.d(LOG_TAG, "initializing repository list")
//        val characterList = mutableListOf<SamodelkinCharacter>()
//        for(i in 1..10) {
//            characterList += CharacterGenerator.generateRandomCharacter(context)
//        }
//        characters = characterList.toList()
//    }

    fun getLeaderboard(): Flow<List<LeaderboardItem>> =  dao.getLeaderboardItem()
//
//    suspend fun getCharacter(uuid: UUID): SamodelkinCharacter? = samodelkinDao.getCharacterById(uuid)
//
    fun addLocationWeather(leaderboardItem: LeaderboardItem){
        coroutineScope.launch{
            dao.addLeaderboardItem(leaderboardItem)
        }
    }

//
//    fun deleteCharacter(samodelkinCharacter: SamodelkinCharacter) {
//        coroutineScope.launch {
//            samodelkinDao.deleteCharacter(samodelkinCharacter)
//        }
//
//    }

}