package com.example.videoplayerstream.ui.player.model

sealed class VideoPlayerEvent {
    /**
     * Toggle play/pause state
     */
    data object PlayPause : VideoPlayerEvent()

    /**
     * Seek to a specific position in the video
     * @param position Position in milliseconds
     */
    data class SeekTo(val position: Long) : VideoPlayerEvent()

    /**
     * Update current playback position
     * @param position Current position in milliseconds
     */
    data class UpdateProgress(
        val position: Long,
        val videoId: String
    ) : VideoPlayerEvent()

    /**
     * Player ready event
     */
    data object PlayerReady : VideoPlayerEvent()

    /**
     * Video completion event
     */
    data object VideoCompleted : VideoPlayerEvent()

    /**
     * Error event
     */
    data class Error(val message: String) : VideoPlayerEvent()

    /**
     * Player state changed event
     */
    data class PlayerStateChanged(val isPlaying: Boolean) : VideoPlayerEvent()
}