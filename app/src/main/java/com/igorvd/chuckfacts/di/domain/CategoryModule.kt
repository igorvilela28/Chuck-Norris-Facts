package com.igorvd.chuckfacts.di.domain

import com.igorvd.chuckfacts.data.jokes.repository.JokeCategoryRepositoryImpl
import com.igorvd.chuckfacts.domain.jokes.repository.JokeCategoryRepository
import dagger.Module
import dagger.Provides

@Module
class CategoryModule {

    @Provides
    fun providesCategoryRepository(
        jokeCategoryRepositoryImpl: JokeCategoryRepositoryImpl
    ): JokeCategoryRepository = jokeCategoryRepositoryImpl
}