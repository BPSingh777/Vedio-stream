package com.example.videoplayerstream.data.constants

import com.example.videoplayerstream.domain.model.VideoContent

object VideoSources {
    val mainVideos = listOf(
        VideoContent(
            id = "big_bunny",
            uri = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            isLocal = false,
            title = "Big Buck Bunny",
            duration = 596000L  // 9:56
        ),
        VideoContent(
            id = "elephants_dream",
            uri = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            isLocal = false,
            title = "Elephants Dream",
            duration = 654000L  // 10:54
        ),
        VideoContent(
            id = "tears_of_steel",
            uri = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
            isLocal = false,
            title = "Tears of Steel",
            duration = 734000L  // 12:14
        ),
        VideoContent(
            id = "sintel",
            uri = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
            isLocal = false,
            title = "Sintel",
            duration = 888000L  // 14:48
        ),
        VideoContent(
            id = "bullrun",
            uri = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
            isLocal = false,
            title = "Going On Bullrun",
            duration = 111000L  // 1:51
        )
    )

    val adVideos = listOf(
        VideoContent(
            id = "ad_android",
            uri = "https://storage.googleapis.com/exoplayer-test-media-1/mp4/android-screens-10s.mp4",
            isLocal = false,
            title = "Android Ad",
            duration = 10000L  // 10 seconds
        ),
        VideoContent(
            id = "ad_counter",
            uri = "https://storage.googleapis.com/exoplayer-test-media-1/mp4/frame-counter-one-hour.mp4",
            isLocal = false,
            title = "Counter Ad",
            duration = 10000L  // Clipped to 10 seconds
        ),
        VideoContent(
            id = "ad_jazz",
            uri = "https://storage.googleapis.com/exoplayer-test-media-0/jazz_in_paris.mp4",
            isLocal = false,
            title = "Jazz Ad",
            duration = 10000L  // Clipped to 10 seconds
        )
    )

    // for local
    val localVideos = listOf(
        VideoContent(
            id = "local_1",
            uri = "videos/ForBiggerBlazes.mp4",
            isLocal = true,
            title = "ForBiggerBlazes",
            duration = 15000L  // 15 seconds in milliseconds
        ),
        VideoContent(
            id = "local_2",
            uri = "videos/WeAreGoingOnBullrun.mp4",
            isLocal = true,
            title = "WeAreGoingOnBullrun",
            duration = 470L
        )
    )
}

