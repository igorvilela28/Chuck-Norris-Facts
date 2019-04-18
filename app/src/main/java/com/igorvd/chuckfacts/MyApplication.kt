package com.igorvd.chuckfacts

import allModules
import android.app.Application
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 *
 * @author Igor Vilela
 * @since 26/07/2018
 */
class MyApplication : Application() {

    @FlowPreview
    override fun onCreate() {
        super.onCreate()
        setupInjector()
        setupTimber()
    }

    @FlowPreview
    private fun setupInjector() {
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(allModules)
        }
    }

    private fun setupTimber() {

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            //TODO: Crash reporting three logging for production app
        }
    }
}