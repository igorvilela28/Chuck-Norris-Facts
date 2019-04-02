package com.igorvd.chuckfacts.di.data


import com.igorvd.chuckfacts.data.ChuckNorrisApi
import com.igorvd.chuckfacts.data.network.ApiClientBuilder
import dagger.Module
import dagger.Provides

/**
 *
 * @author Igor Vilela
 * @since 09/01/2018
 */

private const val CHUCK_NORRIS_API_BASE_URL = "https://api.chucknorris.io/"

@Module
class NetworkModule {

    @Provides
    fun providesChuckNorrisApi(): ChuckNorrisApi {

        return ApiClientBuilder.createService(
                ChuckNorrisApi::class.java,
                CHUCK_NORRIS_API_BASE_URL
        )
    }
}