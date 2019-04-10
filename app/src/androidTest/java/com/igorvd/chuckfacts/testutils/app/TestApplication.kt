package com.igorvd.chuckfacts.testutils.app

import android.app.Activity
import android.app.Application
import android.app.Service
import com.igorvd.chuckfacts.BuildConfig
import com.igorvd.chuckfacts.MyApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import timber.log.Timber
import com.igorvd.chuckfacts.testutils.test_di.DaggerTestAppComponent
import javax.inject.Inject

class TestApplication : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        setupInjector()
        Timber.plant(Timber.DebugTree())
        Timber.d("Test application initialized!")
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector

    private fun setupInjector() {

        DaggerTestAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }


}