package com.igorvd.chuckfacts.di.network

import com.igorvd.chuckfacts.data.jokes.remote.api.ChuckNorrisApi
import com.igorvd.chuckfacts.data.network.ApiClientBuilder
import com.igorvd.chuckfacts.data.network.requests.CallRetryDelays
import com.igorvd.chuckfacts.data.network.requests.RequestMaker
import com.igorvd.chuckfacts.data.network.requests.RequestMakerImpl
import org.koin.dsl.module

private const val CHUCK_NORRIS_API_BASE_URL = "https://api.chucknorris.io/"

val networkModule = module {

    single {
        ApiClientBuilder.createService(
                ChuckNorrisApi::class.java,
                CHUCK_NORRIS_API_BASE_URL
        )
    }

    single {
        RequestMakerImpl(get()) as RequestMaker
    }

    single {
        CallRetryDelays()
    }
}