package com.wordbase.presentation.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wordbase.data.WordbaseRepo

class WordbaseViewModelFactory(private val context: Context, private val mediaPlayer: MediaPlayer) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        private const val LOG_TAG = "WB.ViewModelFactory"
    }

    fun getViewModelClass() = WordbaseViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.d(LOG_TAG, "create() called")
        if(modelClass.isAssignableFrom(getViewModelClass())) {
            Log.d(LOG_TAG, "creating ViewModel: ${getViewModelClass()}")
            return modelClass
                .getConstructor(MediaPlayer::class.java, WordbaseRepo::class.java)
                .newInstance(mediaPlayer, WordbaseRepo.getInstance(context))
        }
        Log.e(LOG_TAG, "Unknown ViewModel: $modelClass")
        throw IllegalArgumentException("Unknown ViewModel")
    }

}