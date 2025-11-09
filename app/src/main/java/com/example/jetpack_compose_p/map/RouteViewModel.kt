package com.example.jetpack_compose_p.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RouteViewModel : ViewModel() {
    private val directionsService = DirectionsService.create()
    private val apiKey = "" // REPLACE WITH YOUR ACTUAL API KEY

    private val _delhiToMumbaiRoute = MutableStateFlow<List<LatLng>>(emptyList())
    val delhiToMumbaiRoute: StateFlow<List<LatLng>> = _delhiToMumbaiRoute

    private val _mumbaiToChennaiRoute = MutableStateFlow<List<LatLng>>(emptyList())
    val mumbaiToChennaiRoute: StateFlow<List<LatLng>> = _mumbaiToChennaiRoute

    private val _loadingState = MutableStateFlow<String>("Not started")
    val loadingState: StateFlow<String> = _loadingState

    private val TAG = "RouteViewModel"

    fun loadRoutes() {
        Log.d(TAG, "loadRoutes() called")
        _loadingState.value = "Starting to load routes..."

        viewModelScope.launch {
            try {
                Log.d(TAG, "Starting API calls...")

                // Route 1: Delhi to Mumbai
                _loadingState.value = "Loading Delhi to Mumbai route..."
                Log.d(TAG, "Calling Directions API for Delhi to Mumbai")

                val delhiMumbaiResponse = directionsService.getDirections(
                    origin = "28.6139,77.2090",
                    destination = "19.0760,72.8777",
                    apiKey = apiKey
                )

                Log.d(TAG, "Delhi-Mumbai Response Status: ${delhiMumbaiResponse.status}")
                Log.d(TAG, "Delhi-Mumbai Routes count: ${delhiMumbaiResponse.routes?.size ?: 0}")

                if (!delhiMumbaiResponse.routes.isNullOrEmpty()) {
                    val encodedPolyline = delhiMumbaiResponse.routes[0]?.overview_polyline?.points
                    Log.d(TAG, "Delhi-Mumbai Encoded polyline: $encodedPolyline")

                    if (!encodedPolyline.isNullOrEmpty()) {
                        val decodedPath = PolylineUtils.decodePolyline(encodedPolyline)
                        Log.d(TAG, "Delhi-Mumbai Decoded points: ${decodedPath.size}")
                        _delhiToMumbaiRoute.value = decodedPath
                        _loadingState.value = "Delhi-Mumbai route loaded: ${decodedPath.size} points"
                    } else {
                        Log.e(TAG, "Delhi-Mumbai: Encoded polyline is empty")
                        _loadingState.value = "Delhi-Mumbai: No polyline data"
                    }
                } else {
                    Log.e(TAG, "Delhi-Mumbai: No routes found in response")
                    Log.e(TAG, "Delhi-Mumbai Status: ${delhiMumbaiResponse.status}")
                    Log.e(TAG, "Delhi-Mumbai Error: ${delhiMumbaiResponse.error_message}")
                    _loadingState.value = "Delhi-Mumbai: No routes available"
                }

                // Route 2: Mumbai to Chennai
                _loadingState.value = "Loading Mumbai to Chennai route..."
                Log.d(TAG, "Calling Directions API for Mumbai to Chennai")

                val mumbaiChennaiResponse = directionsService.getDirections(
                    origin = "19.0760,72.8777",
                    destination = "13.0827,80.2707",
                    apiKey = apiKey
                )

                Log.d(TAG, "Mumbai-Chennai Response Status: ${mumbaiChennaiResponse.status}")
                Log.d(TAG, "Mumbai-Chennai Routes count: ${mumbaiChennaiResponse.routes?.size ?: 0}")

                if (!mumbaiChennaiResponse.routes.isNullOrEmpty()) {
                    val encodedPolyline = mumbaiChennaiResponse.routes[0]?.overview_polyline?.points
                    Log.d(TAG, "Mumbai-Chennai Encoded polyline: $encodedPolyline")

                    if (!encodedPolyline.isNullOrEmpty()) {
                        val decodedPath = PolylineUtils.decodePolyline(encodedPolyline)
                        Log.d(TAG, "Mumbai-Chennai Decoded points: ${decodedPath.size}")
                        _mumbaiToChennaiRoute.value = decodedPath
                        _loadingState.value = "${_loadingState.value}, Mumbai-Chennai: ${decodedPath.size} points"
                    } else {
                        Log.e(TAG, "Mumbai-Chennai: Encoded polyline is empty")
                        _loadingState.value = "${_loadingState.value}, Mumbai-Chennai: No polyline data"
                    }
                } else {
                    Log.e(TAG, "Mumbai-Chennai: No routes found in response")
                    Log.e(TAG, "Mumbai-Chennai Status: ${mumbaiChennaiResponse.status}")
                    Log.e(TAG, "Mumbai-Chennai Error: ${mumbaiChennaiResponse.error_message}")
                    _loadingState.value = "${_loadingState.value}, Mumbai-Chennai: No routes available"
                }

                Log.d(TAG, "All API calls completed")

            } catch (e: Exception) {
                Log.e(TAG, "Error loading routes: ${e.message}", e)
                _loadingState.value = "Error: ${e.message}"
            }
        }
    }
}