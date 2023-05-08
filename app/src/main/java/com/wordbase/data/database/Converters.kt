package com.wordbase.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringListToString(value: List<String>?): String? {
        if (value == null) {
            return null
        }
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToStringList(value: String?): List<String>? {
        if (value == null) {
            return null
        }
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromIntListToString(value: List<Int>?): String? {
        if (value == null) {
            return null
        }
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToIntList(value: String?): List<Int>? {
        if (value == null) {
            return null
        }
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }
}
