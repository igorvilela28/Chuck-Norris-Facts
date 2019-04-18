package com.igorvd.chuckfacts.testutils.app

import android.app.Application
import com.igorvd.chuckfacts.testutils.test_di.*

import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class TestApplication : Application() {

    @FlowPreview
    override fun onCreate() {
        super.onCreate()
        setupInjector()
        Timber.plant(Timber.DebugTree())
        Timber.d("Test application initialized!")
    }

    @FlowPreview
    private fun setupInjector() {

        startKoin {
            androidLogger()
            androidContext(this@TestApplication)
            modules(
                    networkModule,
                    dataSourceModule,
                    repositoryModule,
                    databaseModule,
                    interactorModule,
                    viewModule
            )
        }
    }


}