package com.wordbase.presentation.gameplay

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PlayScreen() {
//    val surfaceWidth = remember { mutableStateOf(0f) }
//    val surfaceHeight = remember { mutableStateOf(0f) }
//
//    Canvas(modifier = Modifier.fillMaxSize()) {
//        surfaceWidth.value = size.width
//        surfaceHeight.value = size.height
//
//        // Call custom drawing functions
//        drawGameObjects(this)
//    }
    AndroidView(
        factory = { context ->
            GameView(context, attrs = null)
        },
        modifier = Modifier.fillMaxSize()
    )
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
