package com.igorvd.chuckfacts

import android.app.Activity
import android.app.Application
import android.app.Service
import com.igorvd.chuckfacts.di.core.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import kotlinx.coroutines.FlowPreview
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @author Igor Vilela
 * @since 26/07/2018
 */
@FlowPreview
class MyApplication : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        setupInjector()
        setupTimber()
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector

    private fun setupInjector() {

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    private fun setupTimber() {

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            //TODO: Crash reporting three logging for production app
        }
    }

}