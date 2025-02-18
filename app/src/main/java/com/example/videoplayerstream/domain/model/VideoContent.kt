package com.example.videoplayerstream.domain.model

data class VideoContent(
    val id: String,
    val uri: String,
    val isLocal: Boolean,
    val title: String,
    val duration: Long
)
