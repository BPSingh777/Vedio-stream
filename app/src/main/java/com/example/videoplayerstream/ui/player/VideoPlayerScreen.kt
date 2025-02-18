package com.example.videoplayerstream.ui.player

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.source.ClippingMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
import com.example.videoplayerstream.data.constants.VideoSources
import kotlinx.coroutines.delay
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.ui.graphics.Color
import androidx.media3.common.PlaybackException


@UnstableApi
// Composable function that represents the video player screen, managing playback and UI state
@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerScreen(
    modifier: Modifier = Modifier,
    viewModel: VideoPlayerViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var currentVideoIndex by remember { mutableStateOf(0) }
    var isPlayingAd by remember { mutableStateOf(false) }
    var hasPlayedAd by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0L) }
    var duration by remember { mutableStateOf(0L) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedTab by remember { mutableStateOf(0) }
    val player = viewModel.player

    // Function to play a local video from assets
    fun playLocalVideo(context: Context, videoPath: String) {
        try {
            val assetFileDescriptor = context.assets.openFd(videoPath)
            val mediaItem = MediaItem.fromUri(Uri.parse("file:///android_asset/$videoPath"))
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
            isPlaying = true
            hasPlayedAd = true
            isPlayingAd = false
            errorMessage = null
            Log.d("VideoPlayer", "Local video playing: $videoPath")
        } catch (e: Exception) {
            errorMessage = "Error playing local video: ${e.message}"
            Log.e("VideoPlayer", "Error playing local video", e)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        // Display error message if there is one
        errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        // Video Player component
        Box(modifier = Modifier.weight(0.7f)) {
            AndroidView(
                factory = { context ->
                    PlayerView(context).apply {
                        this.player = player
                        useController = false // Custom controls will be used instead of the default controller
                        player.addListener(object : Player.Listener {
                            override fun onPlayerError(error: PlaybackException) {
                                errorMessage = "Playback error: ${error.localizedMessage}"
                            }
                        })
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        // Controls for playback and time display
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Time Display showing current position and total duration
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(formatTime(currentPosition))
                Text(formatTime(duration))
            }

            // Progress Slider for seeking through the video
            Slider(
                value = if (duration > 0) currentPosition.toFloat() / duration.toFloat() else 0f,
                onValueChange = { value ->
                    if (!isPlayingAd) {
                        val newPosition = (value * duration).toLong()
                        player.seekTo(newPosition)
                        currentPosition = newPosition
                    }
                },
                enabled = !isPlayingAd,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Playback Control Buttons for rewind, play/pause, and fast forward
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (!isPlayingAd) {
                            player.seekTo(maxOf(0, currentPosition - 10000)) // Rewind 10 seconds
                        }
                    },
                    enabled = !isPlayingAd
                ) {
                    Icon(
                        Icons.Default.Replay10,
                        contentDescription = "Backward 10 seconds",
                        modifier = Modifier.size(32.dp)
                    )
                }

                IconButton(
                    onClick = {
                        isPlaying = !isPlaying  // Toggle play/pause state
                        if (isPlaying) player.play() else player.pause()
                    }
                ) {
                    Icon(
                        if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        modifier = Modifier.size(48.dp)
                    )
                }

                IconButton(
                    onClick = {
                        if (!isPlayingAd) {
                            player.seekTo(minOf(duration, currentPosition + 10000)) // Fast forward 10 seconds
                        }
                    },
                    enabled = !isPlayingAd
                ) {
                    Icon(
                        Icons.Default.Forward10,
                        contentDescription = "Forward 10 seconds",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        // TabRow for switching between Streaming and Local videos
        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 }
            ) {
                Text("Streaming", modifier = Modifier.padding(16.dp))
            }
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 }
            ) {
                Text("Local", modifier = Modifier.padding(16.dp))
            }
        }

        // Playlist of videos displayed in a LazyColumn
        LazyColumn(
            modifier = Modifier
                .weight(0.3f)
                .padding(16.dp)
        ) {
            val videos = if (selectedTab == 0) VideoSources.mainVideos else VideoSources.localVideos

            items(videos) { video ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (!isPlayingAd) {
                                currentVideoIndex = videos.indexOf(video)
                                if (video.isLocal) {
                                    playLocalVideo(context, video.uri)
                                } else {
                                    hasPlayedAd = false
                                    isPlayingAd = false
                                    player.setMediaItem(MediaItem.fromUri(video.uri))
                                    player.prepare()
                                    player.play()
                                    isPlaying = true
                                }
                            }
                        }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = video.title,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    if (isPlayingAd && videos.indexOf(video) == currentVideoIndex) {
                        Text(
                            text = "Ad Playing",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }

        // Position tracking and ad handling logic using LaunchedEffect coroutine
        LaunchedEffect(player) {
            while (true) {
                delay(16)
                if (player.isPlaying) {
                    currentPosition = player.currentPosition // Update current position of the video
                    duration = player.duration // Update total duration of the video

                    // Handle ad insertion for streaming videos only after a certain time
                    if (selectedTab == 0 && !isPlayingAd && !hasPlayedAd && currentPosition >= 30000) {
                        player.pause() // Pause main content before playing ad
                        isPlayingAd = true
                        val mainVideoUri = VideoSources.mainVideos[currentVideoIndex].uri

                        player.setMediaItem(MediaItem.fromUri(VideoSources.adVideos[0].uri))
                        player.prepare()
                        player.addListener(object : Player.Listener {
                            override fun onPlaybackStateChanged(playbackState: Int) {
                                if (playbackState == Player.STATE_ENDED) {
                                    isPlayingAd = false
                                    hasPlayedAd = true
                                    player.removeListener(this)
                                    player.setMediaItem(MediaItem.fromUri(mainVideoUri))
                                    player.prepare()
                                    player.seekTo(31000)
                                    player.play()
                                }
                            }
                        })
                        player.play()  // Start playing ad content
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release() // Release the ExoPlayer resources when the composable is disposed
        }
    }
}

// Formats milliseconds into a string representation of minutes and seconds
fun formatTime(millis: Long): String {
    val seconds = (millis / 1000) % 60
    val minutes = (millis / 1000) / 60
    return String.format("%02d:%02d", minutes, seconds)
}
