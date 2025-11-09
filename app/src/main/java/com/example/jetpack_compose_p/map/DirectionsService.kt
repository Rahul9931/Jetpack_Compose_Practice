package com.example.jetpack_compose_p.map

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface DirectionsService {
    @GET("maps/api/directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String
    ): DirectionsResponse

    companion object {
        private const val BASE_URL = "https://maps.googleapis.com/"
        private const val TAG = "DirectionsService"

        fun create(): DirectionsService {
            Log.d(TAG, "Creating DirectionsService with base URL: $BASE_URL")

            val client = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            Log.d(TAG, "Retrofit instance created successfully")
            return retrofit.create(DirectionsService::class.java)
        }
    }
}