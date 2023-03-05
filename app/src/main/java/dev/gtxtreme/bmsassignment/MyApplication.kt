package dev.gtxtreme.bmsassignment

import android.app.Application
import dev.gtxtreme.bmsassignment.di.appModule
import dev.gtxtreme.bmsassignment.network.NetworkManager
import dev.gtxtreme.bmsassignment.network.networkManagerModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initNetworkManager()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkManagerModule, appModule))
        }
    }

    private fun initNetworkManager() {
        get<NetworkManager>().initialize()
    }
}