package dev.gtxtreme.bmsassignment.network

import dev.gtxtreme.bmsassignment.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

// This adds the api_key query param to each request, as is required by the TMDB API

class ApiKeyInterceptor: Interceptor {
    companion object {
        private const val APP_ID = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val tmdbApiKey = BuildConfig.TMDB_API_KEY
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(APP_ID, tmdbApiKey).build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}