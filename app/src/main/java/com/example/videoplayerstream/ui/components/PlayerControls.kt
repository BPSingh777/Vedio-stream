package com.example.videoplayerstream.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Composable function for video player controls including progress bar, time display, and play/pause button
@Composable
fun PlayerControls(
    isPlaying: Boolean,
    progress: Float,
    duration: Long,
    currentPosition: Long,
    onPlayPause: () -> Unit,
    onSeek: (Float) -> Unit,
    isAdPlaying: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display ad playing status if applicable
        if (isAdPlaying) {
            Text(
                text = "Ad playing: ${formatTime(currentPosition)} / ${formatTime(duration)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        // Time display showing current position and total duration
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTime(currentPosition),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = formatTime(duration),
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Slider for seeking through the video
        Slider(
            value = progress,
            onValueChange = onSeek,
            enabled = !isAdPlaying,
            valueRange = 0f..100f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        // Play/Pause button
        IconButton(
            onClick = onPlayPause,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

// Formats milliseconds into a string representation of minutes and seconds
private fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}