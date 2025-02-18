package com.example.videoplayerstream.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import com.example.videoplayerstream.domain.model.PlaylistWithAd
import com.example.videoplayerstream.ui.player.model.VideoPlayerEvent
import com.example.videoplayerstream.ui.player.model.VideoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for managing video playback state and ExoPlayer instance
@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    val player: ExoPlayer
) : ViewModel() {

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    public override fun onCleared() {
        super.onCleared()
        player.release()
    }
}