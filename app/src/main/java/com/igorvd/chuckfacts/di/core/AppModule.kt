package com.igorvd.chuckfacts.di.core

import android.content.Context
import com.igorvd.chuckfacts.MyApplication
import com.igorvd.chuckfacts.data.jokes.remote.api.ChuckNorrisApi
import com.igorvd.chuckfacts.data.network.ApiClientBuilder
import com.igorvd.chuckfacts.data.network.requests.RequestMaker
import com.igorvd.chuckfacts.data.network.requests.RequestMakerImpl
import com.igorvd.chuckfacts.data.network.requests.CallRetryDelays
import com.igorvd.chuckfacts.di.data.LocalModule
import com.igorvd.chuckfacts.di.data.RemoteModule
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Módulo que define/inclui todos objetos que devem ser injetados como [Singleton]. Ou seja,
 * você deve declarar ou incluir módulos que provem objetos a terem uma única instância compartilhada
 * por toda a aplicação
 *
 * @author Igor Vilela
 * @since 28/12/17
 */
@Module(includes = [
    LocalModule::class,
    RemoteModule::class
])
class AppModule {

    companion object {
        private const val CHUCK_NORRIS_API_BASE_URL = "https://api.chucknorris.io/"
    }

    @Singleton
    @Provides
    @Named("application")
    fun providesApplicationContext(application: MyApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesChuckNorrisApi(): ChuckNorrisApi {

        return ApiClientBuilder.createService(
            ChuckNorrisApi::class.java,
            CHUCK_NORRIS_API_BASE_URL
        )
    }

    @Provides
    @Singleton
    fun providesRetryDelays() = CallRetryDelays()

    @Provides
    @Singleton
    fun providesRequestMaker(requestMaker: RequestMakerImpl): RequestMaker = requestMaker
}