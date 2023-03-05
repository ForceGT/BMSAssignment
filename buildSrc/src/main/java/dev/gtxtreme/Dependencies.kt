

object Dependencies {

    object Compose {

        const val material = "androidx.compose.material:material"
        const val animation = "androidx.compose.animation:animation"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val foundation = "androidx.compose.foundation:foundation"
        const val composeBom = "androidx.compose:compose-bom:${Versions.Compose.composeBom}"
        const val ui = "androidx.compose.ui:ui"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.Compose.navigation}"
    }

    object Kotlin {
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.kotlinxSerialization}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.Koin.koinAndroid}"
        const val android = "io.insert-koin:koin-android:${Versions.Koin.koinAndroid}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.Koin.koinCompose}"
    }
    object OkHttp {
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.OkHttp.logger}"
    }

    object KtorClient {
        const val core = "io.ktor:ktor-client-core:${Versions.KtorClient.core}"
        const val logging = "io.ktor:ktor-client-logging:${Versions.KtorClient.core}"
        const val kotlinxSerialization =
            "io.ktor:ktor-serialization-kotlinx-json:${Versions.KtorClient.core}"
        const val okHttpEngine = "io.ktor:ktor-client-okhttp:${Versions.KtorClient.core}"
        const val contentNegotiation =
            "io.ktor:ktor-client-content-negotiation:${Versions.KtorClient.core}"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:${Versions.Android.coreKtx}"
        const val lifecyleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecyleKtx}"
        const val activity = "androidx.activity:activity-compose:${Versions.Android.composeActivity}"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Android.lifecyleKtx}"
    }

    object Image {
        const val coil = "io.coil-kt:coil-compose:${Versions.Coil.coilCompose}"
    }

}