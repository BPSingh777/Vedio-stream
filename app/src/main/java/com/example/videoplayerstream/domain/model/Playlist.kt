package com.example.videoplayerstream.domain.model

// Represents a collection of videos grouped as a playlist
data class Playlist(
    val id: String,
    val name: String,
    val videos: List<VideoContent>
)

// Represents a main video content with an associated ad and its position
data class PlaylistWithAd(
    val mainContent: VideoContent,
    val adContent: VideoContent,
    val position: Int // Position at which the ad should be inserted in the main content
)