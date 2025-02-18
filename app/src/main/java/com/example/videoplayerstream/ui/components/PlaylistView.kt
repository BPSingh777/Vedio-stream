package com.example.videoplayerstream.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.videoplayerstream.domain.model.VideoContent

// Composable function that displays a scrollable list of videos with selection functionality
@Composable
fun PlaylistView(
    videos: List<VideoContent>,
    currentVideoIndex: Int,
    onVideoSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight()
    ) {
        items(videos) { video ->
            val index = videos.indexOf(video)
            val isSelected = index == currentVideoIndex

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onVideoSelect(index) }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Video Title
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = video.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurface
                    )
                    // Duration
                    Text(
                        text = formatDuration(video.duration),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}
// Formats milliseconds into a string representation of minutes and seconds
private fun formatDuration(durationMs: Long): String {
    val seconds = (durationMs / 1000) % 60
    val minutes = (durationMs / 1000) / 60
    return String.format("%d:%02d", minutes, seconds)
}