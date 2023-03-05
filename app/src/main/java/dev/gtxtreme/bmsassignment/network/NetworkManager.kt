package dev.gtxtreme.bmsassignment.network

import android.net.Uri
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class NetworkManager {

    @PublishedApi
    internal lateinit var httpClient: HttpClient

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3"
        const val IMAGE_FULL_RESOLUTION_BASE_URL = "https://image.tmdb.org/t/p/original"
        const val IMAGE_PROFILE_BASE_URL = "https://image.tmdb.org/t/p/h632"
    }

    fun initialize() {
        httpClient = HttpClient(OkHttp) {
            engine {
                preconfigured = getOkHttpClient()
            }

            install(ContentNegotiation) {
                json(jsonSerializer)
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            addInterceptor(ApiKeyInterceptor())
        }.build()
    }

    suspend inline fun <reified T> makeGETRequest(
        url: String,
        queryParams: HashMap<String, String> = hashMapOf(),
        headerList: Map<String, String> = mapOf(),
    ): NetworkResponse<T> {

        val uriBuilder = Uri.parse(BASE_URL + url)
            .buildUpon()
        // Apply query params
        queryParams.forEach {
            uriBuilder.appendQueryParameter(it.key, it.value)
        }
        val finalUrl = uriBuilder.build().toString()

        val response = httpClient.request(finalUrl) {
            // Add headers, if required
            headers {
                headerList.forEach { (key, value) -> append(key, value) }
            }
            method = HttpMethod.Get
        }
        return mapResponseToNetworkResponse(response)
    }


    @PublishedApi
    internal suspend inline fun <reified T> mapResponseToNetworkResponse(httpResponse: HttpResponse): NetworkResponse<T> {
        val networkResponse = if (httpResponse.status.isSuccess()) {
            val responseData: T = httpResponse.body()
            NetworkResponse.Success(responseData, statusCode = httpResponse.status.value)
        } else {
            var data: Result<Any?> = runCatching { httpResponse.bodyAsText() }

            if (data.isFailure) {
                data = runCatching { httpResponse.body<T>() }
            }

            val errorResponse =
                ErrorResponse(httpResponse.status.description, httpResponse.status.value)
            NetworkResponse.Error(
                error = errorResponse,
                data = data.getOrThrow(),
                statusCode = httpResponse.status.value
            )
        }
        return networkResponse
    }
}