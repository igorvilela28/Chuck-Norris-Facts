package com.igorvd.chuckfacts.di.data


import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSource
import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.remote.api.ChuckNorrisApi
import com.igorvd.chuckfacts.data.network.ApiClientBuilder
import com.igorvd.chuckfacts.data.network.requests.RequestMaker
import com.igorvd.chuckfacts.data.network.requests.RequestMakerImpl
import dagger.Module
import dagger.Provides

/**
 *
 * @author Igor Vilela
 * @since 09/01/2018
 */

private const val CHUCK_NORRIS_API_BASE_URL = "https://api.chucknorris.io/"

@Module
class RemoteModule {

    @Provides
    fun providesChuckNorrisApi(): ChuckNorrisApi {

        return ApiClientBuilder.createService(
                ChuckNorrisApi::class.java,
                CHUCK_NORRIS_API_BASE_URL
        )
    }

    @Provides
    fun providesRequestMaker(requestMaker: RequestMakerImpl): RequestMaker = requestMaker

    @Provides
    fun providesCategoryRemoteDataSource(
        dataSource: JokeCategoryRemoteDataSourceImpl
    ): JokeCategoryRemoteDataSource = dataSource
}