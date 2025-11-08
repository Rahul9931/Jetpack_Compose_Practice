package com.example.jetpack_compose_p.map

//class MapActivity {
//}

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    SimpleMapScreen()
//                    MapWithMarkers()
                    MapWithMarkersAndLines()
                }
            }
        }
    }
}

@Composable
fun SimpleMapScreen() {
    // Define a location (New Delhi coordinates)
    val delhi = LatLng(28.6139, 77.2090)

    // Remember camera position - this keeps the map state across recompositions
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(delhi, 10f) // Zoom level 10f
    }

    // Google Map Composable - this displays the actual map
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    )
}

@Composable
fun MapWithMarkers() {
    // Define locations
    val delhi = LatLng(28.6139, 77.2090)
    val mumbai = LatLng(19.0760, 72.8777)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(delhi, 4f) // Zoom out to see both markers
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Marker for Delhi
        Marker(
            state = MarkerState(position = delhi),
            title = "Delhi",
            snippet = "Capital of India"
        )

        // Marker for Mumbai
        Marker(
            state = MarkerState(position = mumbai),
            title = "Mumbai",
            snippet = "Financial capital of India"
        )
    }
}

@Composable
fun MapWithMarkersAndLines() {
    // Define locations
    val delhi = LatLng(28.6139, 77.2090)
    val mumbai = LatLng(19.0760, 72.8777)
    val chennai = LatLng(13.0827, 80.2707)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(delhi, 5f)
    }

    // Create list of points for polyline (line)
    val delhiToMumbai = listOf(delhi, mumbai)
    val mumbaiToChennai = listOf(mumbai, chennai)

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Marker for Delhi
        Marker(
            state = MarkerState(position = delhi),
            title = "Delhi",
            snippet = "Capital of India"
        )

        // Marker for Mumbai
        Marker(
            state = MarkerState(position = mumbai),
            title = "Mumbai",
            snippet = "Financial capital"
        )

        // Marker for Chennai
        Marker(
            state = MarkerState(position = chennai),
            title = "Chennai",
            snippet = "Capital of Tamil Nadu"
        )

        // Draw line between Delhi and Mumbai
        Polyline(
            points = delhiToMumbai,
            color = Color.Blue,
            width = 5f
        )

        // Draw line between Mumbai and Chennai
        Polyline(
            points = mumbaiToChennai,
            color = Color.Red,
            width = 5f
        )
    }
}

@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}