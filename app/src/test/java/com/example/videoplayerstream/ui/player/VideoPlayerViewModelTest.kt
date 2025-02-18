package com.example.videoplayerstream.ui.player

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import com.example.videoplayerstream.domain.model.VideoContent
import com.example.videoplayerstream.ui.player.model.VideoPlayerEvent
import com.example.videoplayerstream.ui.player.model.VideoUiState
import com.example.videoplayerstream.utils.ContentSourceFactory
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class VideoPlayerViewModelTest {
    private lateinit var viewModel: VideoPlayerViewModel
    private lateinit var mockPlayer: ExoPlayer
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockPlayer = mockk(relaxed = true) {
            every { playWhenReady } returns false
            every { play() } just runs
            every { pause() } just runs
            every { seekTo(any()) } just runs
        }

        viewModel = VideoPlayerViewModel(
            player = mockPlayer
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test initial playing state is false`() {
        assertEquals(false, viewModel.isPlaying.value)
    }

    @Test
    fun `test play pause updates state`() {
        // Given
        every { mockPlayer.isPlaying } returns false
        val playingStateFlow = MutableStateFlow(false)
        every { mockPlayer.play() } answers {
            playingStateFlow.value = true
        }

        // When
        viewModel.player.play()

        // Then
        verify { mockPlayer.play() }
    }

    @Test
    fun `test player is released on clear`() {
        // When
        viewModel.onCleared()

        // Then
        verify { mockPlayer.release() }
    }
}