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


@Module
class RemoteModule {



    @Provides
    fun providesCategoryRemoteDataSource(
        dataSource: JokeCategoryRemoteDataSourceImpl
    ): JokeCategoryRemoteDataSource = dataSource
}