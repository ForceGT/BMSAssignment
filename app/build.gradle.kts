import dev.gtxtreme.AppConfig
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android")
    kotlin("plugin.serialization") version Versions.Kotlin.kotlin
}

android {
    namespace = AppConfig.nameSpace
    compileSdk = AppConfig.compileSdkVersion
    val props = Properties().apply {
        load(rootProject.file("local.properties").reader())
    }

    defaultConfig {
        applicationId = AppConfig.nameSpace
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            buildConfigField("String", "TMDB_API_KEY", props["TMDB_API_KEY"].toString())
        }
        getByName("debug") {
            isDebuggable = true
            buildConfigField("String", "TMDB_API_KEY", props["TMDB_API_KEY"].toString())
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}

dependencies {


    // Android
    with(Dependencies.Android) {
        implementation(coreKtx)
        implementation(lifecyleKtx)
        implementation(activity)
        implementation(viewModelCompose)
    }

    // Kotlin

    with(Dependencies.Kotlin){
        implementation(serialization)
        implementation(coroutines)
    }


    // Compose
    with(Dependencies.Compose) {
        implementation(platform(composeBom))
        androidTestImplementation(platform(composeBom))
        implementation(animation)
        implementation(uiTooling)
        implementation(ui)
        implementation(material)
        implementation(navigation)
    }

    // KtorClient- For networking related operations
    with(Dependencies.KtorClient) {
        implementation(core)
        implementation(logging)
        implementation(kotlinxSerialization)
        implementation(okHttpEngine)
        implementation(contentNegotiation)
        implementation(Dependencies.OkHttp.loggingInterceptor)
    }

    // Koin
    with(Dependencies.Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
    }

    // Image related stuff
    implementation(Dependencies.Image.coil)

}