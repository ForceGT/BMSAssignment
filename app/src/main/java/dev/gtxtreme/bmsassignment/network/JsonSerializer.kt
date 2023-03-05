package dev.gtxtreme.bmsassignment.network

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
val jsonSerializer = Json {
    prettyPrint = true
    explicitNulls = false
    isLenient = true
    ignoreUnknownKeys = true
}