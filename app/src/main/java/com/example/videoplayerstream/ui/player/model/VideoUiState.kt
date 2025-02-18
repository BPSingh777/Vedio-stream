package com.example.videoplayerstream.ui.player.model

import com.example.videoplayerstream.domain.model.VideoContent

// Sealed class representing different states of the video player UI
sealed class VideoUiState {
    // Represents the loading state of the video player
    data object Loading : VideoUiState()

    // Represents an error state with an associated error message
    data class Error(val message: String) : VideoUiState()

    // Represents the playing state with detailed information about the current video and playback
    data class Playing(
        val mainContent: VideoContent,
        val adContent: VideoContent,
        val isPlaying: Boolean = false,
        val progress: Long = 0L,
        val duration: Long = 0L,
        val currentVideoIndex: Int = 0,
        val buffering: Boolean = false
    ) : VideoUiState()
}