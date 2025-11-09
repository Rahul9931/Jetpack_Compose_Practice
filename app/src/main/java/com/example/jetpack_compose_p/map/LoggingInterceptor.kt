package com.example.jetpack_compose_p.map

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Log the request
        Log.d("API_REQUEST", "URL: ${request.url}")
        Log.d("API_REQUEST", "Method: ${request.method}")

        val response = chain.proceed(request)

        // Log the response
        val responseBody = response.body
        val responseBodyString = responseBody?.string() ?: ""

        Log.d("API_RESPONSE", "Status Code: ${response.code}")
        Log.d("API_RESPONSE", "Response Body: $responseBodyString")

        // Pretty print JSON if it's JSON
        try {
            val json = JSONObject(responseBodyString)
            val prettyJson = json.toString(4)
            Log.d("API_RESPONSE_JSON", "Formatted JSON:\n$prettyJson")
        } catch (e: Exception) {
            Log.d("API_RESPONSE", "Response is not JSON or is empty")
        }

        // Recreate the response because we consumed the original body
        val newResponseBody = responseBodyString.toResponseBody(responseBody?.contentType())
        return response.newBuilder().body(newResponseBody).build()
    }
}