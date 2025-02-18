package com.example.videoplayerstream.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView

// Composable function that displays a video player using ExoPlayer's PlayerView with a 16:9 aspect ratio
@Composable
fun VideoDisplay(
    player: Player,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                this.player = player
                useController = false // We're using our own controls
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
    )
}