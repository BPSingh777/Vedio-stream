package com.example.videoplayerstream.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Composable function that provides buttons for selecting streaming or local video sources
@Composable
fun VideoSourceSelector(
    onVideoSelected: (Uri, Boolean) -> Unit
) {
    // Launcher for selecting local video files
    val localVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onVideoSelected(it, true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Button to play a streaming video
        Button(
            onClick = {
                onVideoSelected(
                    Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
                    false
                )
            }
        ) {
            Text("Play Streaming Video")
        }

        // Button to select and play a local video file
        Button(
            onClick = { localVideoLauncher.launch("video/*") }
        ) {
            Text("Select Local Video")
        }
    }
}