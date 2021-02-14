package dev.localiza.rentcar

import android.app.Application
import dev.localiza.rentcar.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class RentCarAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin()
        configureTimber()
    }

    private fun configureKoin() {
        startKoin {
            androidContext(this@RentCarAplication)
            modules(
                AppModule.getModules()
            )
        }
    }

    private fun configureTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}