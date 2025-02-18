# Video Player Stream

A modern Android video player application built with ExoPlayer and Jetpack Compose that supports both streaming and local video playback with advanced features like mid-roll ad insertion.

## Features

- **Video Playback**
    - Streaming video support
    - Local video playback
    - Custom playback controls
    - Smooth video transitions

- **Ad Integration**
    - Mid-roll ad insertion at 30 seconds
    - Seamless transition between content and ads
    - Ad completion tracking

- **User Interface**
    - Material Design 3 implementation
    - Tab-based content navigation
    - Custom video controls
    - Progress tracking
    - Error handling
    - Loading states

- **Technical Features**
    - Content stitching
    - Playlist management
    - Error handling
    - Progress tracking
    - Time formatting

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/snehaawate/videoplayerstream.git
   cd videoplayerstream
   ```

2. **Configure Android Studio**
    - Open Android Studio
    - Go to File → Open
    - Navigate to the cloned project directory
    - Click "OK" to open the project

3. **Configure Local Properties**
    - Create a `local.properties` file in the project root if it doesn't exist
    - Add your Android SDK path:
      ```properties
      sdk.dir=/path/to/your/Android/sdk
      ```

4. **Add Test Videos (Optional)**
    - Create an `assets/videos` directory in the `app/src/main` folder
    - Add your test videos to this directory
    - Update the `VideoSources.kt` file with your video information

5. **Build and Run**
   ```bash
   # Build the project
   ./gradlew build

   # Install on connected device/emulator
   ./gradlew installDebug
   ```

6. **Running Tests**
   ```bash
   # Run all tests
   ./gradlew test

   # Run specific test class
   ./gradlew test --tests "com.example.videoplayerstream.ui.player.VideoPlayerViewModelTest"
   ```

## Tech Stack

### Core Technologies
- **Language:** Kotlin
- **Platform:** Android
- **Minimum SDK:** 21
- **Target SDK:** Latest

### UI Framework
- Jetpack Compose
- Material Design 3
- Compose Navigation

### Media Player
- ExoPlayer (Media3)
- MediaSource Factory
- Custom PlayerView

### Architecture Components
- MVVM Architecture
- ViewModel
- StateFlow
- Lifecycle Components

### Dependency Injection
- Hilt
- Dagger

### Asynchronous Programming
- Kotlin Coroutines
- Flow
- StateFlow

### Testing
- JUnit
- Mockk for Kotlin
- Coroutines Test

### Build Tools
- Gradle
- Android Build Tools

### Version Control
- Git
- GitHub

## Architecture

- MVVM Architecture
- Dependency Injection with Hilt
- Jetpack Compose UI
- ExoPlayer Integration
- Kotlin Coroutines & Flow

## Project Structure

```
com.example.videoplayerstream/
├── data/
│   └── constants/
│       └── VideoSources.kt
├── di/
│   ├── AppModule.kt
│   └── DispatcherModule.kt
├── domain/
│   └── model/
│       ├── Playlist.kt
│       ├── PlaylistWithAd.kt
│       └── VideoContent.kt
├── ui/
│   ├── components/
│   │   ├── EnhancedPlayerControls.kt
│   │   ├── ErrorScreen.kt
│   │   ├── LoadingScreen.kt
│   │   ├── PlayerControls.kt
│   │   ├── PlaylistView.kt
│   │   ├── VideoDisplay.kt
│   │   └── VideoSourceSelector.kt
│   └── player/
│       ├── VideoPlayerScreen.kt
│       ├── VideoPlayerViewModel.kt
│       └── model/
│           ├── VideoPlayerEvent.kt
│           └── VideoUiState.kt
└── utils/
    └── ContentSourceFactory.kt
```

## Implementation Details

### Video Player Features
1. **Content Playback**
    - Supports both streaming and local video playback
    - Handles transitions between videos smoothly
    - Implements basic playback controls (play/pause)

2. **Content Stitching**
    - Plays main content video
    - Transitions to ad content at 30 seconds
    - Returns to main content after ad completion
    - Handles transitions smoothly

3. **Error Handling**
    - Manages playback errors
    - Displays user-friendly error messages
    - Implements error recovery mechanisms

4. **Progress Tracking**
    - Tracks video progress
    - Displays current position and duration
    - Supports seeking through content

## Future Improvements

1. **Enhanced Playback Features**
    - Picture-in-Picture mode support
    - Background playback capability
    - Quality selection options
    - Playback speed controls
    - Subtitle support

2. **Advanced Ad Integration**
    - Dynamic ad insertion points
    - Pre-roll and post-roll ads
    - Ad targeting based on content
    - Skip ad functionality
    - Ad analytics and tracking

3. **User Experience**
    - Custom video thumbnails
    - Video chapters support
    - Gesture controls for volume and brightness
    - Double-tap to seek
    - Mini player mode

4. **Content Management**
    - Playlist creation and management
    - Watch history
    - Favorites list
    - Continue watching feature
    - Queue management

5. **Technical Improvements**
    - Offline playback support
    - Adaptive streaming optimization
    - Battery consumption optimization
    - Memory management improvements
    - Cache management system

6. **Testing and Quality**
    - Increase unit test coverage
    - Add integration tests
    - UI automation tests
    - Performance testing
    - Network condition handling tests

7. **Architecture and Code**
    - Implement Clean Architecture
    - Add Repository pattern
    - Improve error handling
    - Add logging system
    - Implementation of analytics

8. **Security**
    - DRM support
    - Content encryption
    - Secure playback
    - Token-based authentication

