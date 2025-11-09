package com.example.jetpack_compose_p.map

import android.util.Log
import com.google.android.gms.maps.model.LatLng

object PolylineUtils {
    private const val TAG = "PolylineUtils"

    fun decodePolyline(encoded: String?): List<LatLng> {
        if (encoded.isNullOrEmpty()) {
            Log.e(TAG, "Encoded polyline is null or empty")
            return emptyList()
        }

        Log.d(TAG, "Starting to decode polyline. Encoded string length: ${encoded.length}")
        Log.d(TAG, "Encoded string: $encoded")

        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        try {
            while (index < len) {
                var b: Int
                var shift = 0
                var result = 0
                do {
                    b = encoded[index++].code - 63
                    result = result or (b and 0x1f shl shift)
                    shift += 5
                } while (b >= 0x20)
                val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
                lat += dlat

                shift = 0
                result = 0
                do {
                    b = encoded[index++].code - 63
                    result = result or (b and 0x1f shl shift)
                    shift += 5
                } while (b >= 0x20)
                val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
                lng += dlng

                val latLng = LatLng(lat / 1E5, lng / 1E5)
                poly.add(latLng)
            }

            Log.d(TAG, "Successfully decoded polyline. Points decoded: ${poly.size}")
            if (poly.isNotEmpty()) {
                Log.d(TAG, "First point: ${poly.first()}")
                Log.d(TAG, "Last point: ${poly.last()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error decoding polyline: ${e.message}", e)
        }

        return poly
    }
}