package com.wordbase.data

data class WordItem(
    val word: String,
    val definition: String,
    val proficiency: Double = 0.0
)