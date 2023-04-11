package com.wordbase

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.wordbase.presentation.navigation.WordbaseNavHost
import com.wordbase.presentation.viewmodel.WordbaseViewModel
import com.wordbase.ui.theme.WordBaseTheme


class MainActivity : ComponentActivity() {
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this
        mediaPlayer = MediaPlayer.create(context, R.raw.vintage_organ)
        mediaPlayer.start()
        mediaPlayer.isLooping = true
        val mViewModel = WordbaseViewModel(mediaPlayer)


        setContent {
            WordBaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    WordbaseNavHost(navController = navController, wordbaseViewModel = mViewModel, context = context)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WordBaseTheme {
        Greeting("Android")
    }
}