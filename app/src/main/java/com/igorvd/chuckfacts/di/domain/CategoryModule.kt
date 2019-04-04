package com.igorvd.chuckfacts.di.domain


import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSource
import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.remote.api.ChuckNorrisApi
import com.igorvd.chuckfacts.data.jokes.repository.JokeCategoryRepositoryImpl
import com.igorvd.chuckfacts.data.network.ApiClientBuilder
import com.igorvd.chuckfacts.data.network.requests.RequestMaker
import com.igorvd.chuckfacts.data.network.requests.RequestMakerImpl
import com.igorvd.chuckfacts.domain.jokes.repository.JokeCategoryRepository
import dagger.Module
import dagger.Provides

/**
 *
 * @author Igor Vilela
 * @since 09/01/2018
 */


@Module
class CategoryModule {

    @Provides
    fun providesCategoryRepository(
        jokeCategoryRepositoryImpl: JokeCategoryRepositoryImpl
    ): JokeCategoryRepository = jokeCategoryRepositoryImpl
}