package com.wordbase.presentation.gameplay

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.viewinterop.AndroidView
import com.wordbase.presentation.viewmodel.WordbaseViewModel

@Composable
fun PlayScreen(wordbaseViewModel: WordbaseViewModel) {

    when (wordbaseViewModel.currentView.value) {
        is GameViewType.BatView -> {
            AndroidView(
                factory = { context ->
                    BatView(context, attrs = null)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        is GameViewType.SpellView -> {
            AndroidView(
                factory = { context ->
                    SpellView(context, attrs = null)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        is GameViewType.PitcherView -> {
            AndroidView(
                factory = { context ->
                    PitcherView(context, attrs = null)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        else -> {}
    }
}

private fun drawGameObjects(drawScope: DrawScope) {
    with(drawScope) {
        // Draw game objects on the canvas
        // Example: draw a circle as a placeholder
        drawCircle(
            color = Color.Blue,
            radius = 20f,
            center = Offset(x = size.width / 2, y = size.height / 2)
        )
    }
}
