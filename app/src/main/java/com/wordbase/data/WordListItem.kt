package com.wordbase.data

data class WordListItem (
    val id: Int,
    val title: String,
    val proficiency: Double = 0.0,
    val words: ArrayList<String>,
    val grades: ArrayList<Int> = arrayListOf(0,12),
    val added: Boolean = true
)