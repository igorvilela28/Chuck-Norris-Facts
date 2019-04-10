package com.igorvd.chuckfacts.testutils.test_di

import android.content.Context
import com.igorvd.chuckfacts.data.jokes.remote.api.ChuckNorrisApi
import com.igorvd.chuckfacts.data.network.ApiClientBuilder
import com.igorvd.chuckfacts.data.network.requests.RequestMaker
import com.igorvd.chuckfacts.data.network.requests.RequestMakerImpl
import com.igorvd.chuckfacts.di.data.LocalModule
import com.igorvd.chuckfacts.di.data.RemoteModule
import com.igorvd.chuckfacts.testutils.app.TestApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [
    LocalModule::class,
    RemoteModule::class
])
class TestAppModule {

    companion object {
        private const val MOCK_SERVER_URL = "http://localhost:8080/"
    }

    @Singleton
    @Provides
    @Named("application")
    fun providesApplicationContext(application: TestApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesChuckNorrisApi(): ChuckNorrisApi {

        return ApiClientBuilder.createService(
            ChuckNorrisApi::class.java,
            MOCK_SERVER_URL
        )
    }

    @Provides
    @Singleton
    fun providesRequestMaker(requestMaker: RequestMakerImpl): RequestMaker = requestMaker
}