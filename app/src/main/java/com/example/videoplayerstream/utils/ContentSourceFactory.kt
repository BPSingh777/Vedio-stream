package com.example.videoplayerstream.utils

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.source.MediaSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

// Factory class for creating MediaSource objects from URIs for local and streaming video content
class ContentSourceFactory @Inject constructor(
    @ApplicationContext private val context: Context,
    private val mediaSourceFactory: MediaSource.Factory
) {
    fun createSource(uri: String, isLocal: Boolean): MediaSource {
        val contentUri = Uri.parse(uri)
        val mediaItem = when {
            isLocal -> {
                MediaItem.fromUri(contentUri)
            }
            else -> {
                MediaItem.Builder()
                    .setUri(contentUri)
                    .setMimeType("video/mp4")
                    .build()
            }
        }
        return mediaSourceFactory.createMediaSource(mediaItem)
    }
}