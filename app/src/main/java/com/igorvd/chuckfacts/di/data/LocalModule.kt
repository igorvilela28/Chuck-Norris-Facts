package com.igorvd.chuckfacts.di.data

import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSource
import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSourceImpl
import dagger.Module
import dagger.Provides

/**
 *
 * @author Igor Vilela
 * @since 24/01/2018
 */

@Module
class LocalModule {

    @Provides
    fun providesCategoryLocalDataSource(
        dataSource: JokeCategoryLocalDataSourceImpl
    ): JokeCategoryLocalDataSource = dataSource


}