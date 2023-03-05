package dev.gtxtreme.bmsassignment.network

import org.koin.dsl.module

val networkManagerModule = module {
    single {
        NetworkManager()
    }
}