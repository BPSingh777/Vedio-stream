package com.example.videoplayerstream.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EnhancedPlayerControls(
    isPlaying: Boolean,
    progress: Float,
    duration: Long,
    currentPosition: Long,
    isAdPlaying: Boolean,
    canPlayPrevious: Boolean,
    canPlayNext: Boolean,
    onPlayPause: () -> Unit,
    onSeek: (Float) -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Time display and ad playing indicator
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
            if (isAdPlaying) {
                Text(
                    text = "Ad Playing",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Text(
                text = formatTime(duration),
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Progress bar (disabled during ad playback)
        Slider(
            value = progress,
            onValueChange = { if (!isAdPlaying) onSeek(it) },
            enabled = !isAdPlaying,
            valueRange = 0f..100f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        // Playback control button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onPrevious,
                enabled = canPlayPrevious && !isAdPlaying
            ) {
                Icon(
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = "Previous"
                )
            }

            IconButton(
                onClick = onPlayPause,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    modifier = Modifier.size(32.dp)
                )
            }

            IconButton(
                onClick = onNext,
                enabled = canPlayNext && !isAdPlaying
            ) {
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "Next"
                )
            }
        }
    }
}
// Formats milliseconds into a string representation of minutes and seconds
private fun formatTime(millis: Long): String {
    val seconds = (millis / 1000) % 60
    val minutes = (millis / 1000) / 60
    return String.format("%d:%02d", minutes, seconds)
}