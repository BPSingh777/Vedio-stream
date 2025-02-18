package com.example.videoplayerstream.ui.player

import org.junit.Assert.assertEquals
import org.junit.Test

class TimeFormatTest {
    @Test
    fun `test formatTime with various durations`() {
        val testCases = listOf(
            0L to "00:00",
            1000L to "00:01",
            60000L to "01:00",
            61000L to "01:01",
            3600000L to "60:00",
            3661000L to "61:01"
        )

        testCases.forEach { (input, expected) ->
            assertEquals(expected, formatTime(input))
        }
    }
}
