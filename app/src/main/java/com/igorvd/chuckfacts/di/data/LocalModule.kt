package com.igorvd.chuckfacts.di.data

import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSource
import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.local.JokeLocalDataSource
import com.igorvd.chuckfacts.data.jokes.local.JokeLocalDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class LocalModule {

    @Provides
    fun providesCategoryLocalDataSource(
        dataSource: JokeCategoryLocalDataSourceImpl
    ): JokeCategoryLocalDataSource = dataSource

    @Provides
    fun providesJokeLocalDataSource(
        dataSource: JokeLocalDataSourceImpl
    ): JokeLocalDataSource = dataSource


}