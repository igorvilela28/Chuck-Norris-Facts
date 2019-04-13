package com.igorvd.chuckfacts.di.data


import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    @Provides
    fun providesCategoryRemoteDataSource(
        dataSource: JokeCategoryRemoteDataSourceImpl
    ): JokeCategoryRemoteDataSource = dataSource

    @Provides
    fun providesJokeRemoteDataSource(
        dataSource: JokeRemoteDataSourceImpl
    ): JokeRemoteDataSource = dataSource
}