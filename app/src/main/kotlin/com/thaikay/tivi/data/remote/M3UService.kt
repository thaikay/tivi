package com.thaikay.tivi.data.remote

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class M3UService {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun fetchM3U(url: String): String {
        return try {
            val request = okhttp3.Request.Builder()
                .url(url)
                .header("User-Agent", "ThaiTV/1.0")
                .build()

            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.string() ?: throw Exception("Empty response")
            } else {
                throw Exception("HTTP ${response.code}: ${response.message}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to fetch M3U: ${e.message}")
        }
    }
}
