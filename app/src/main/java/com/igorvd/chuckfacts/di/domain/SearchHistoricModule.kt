package com.igorvd.chuckfacts.di.domain

import com.igorvd.chuckfacts.data.jokes.repository.SearchHistoricRepositoryImpl
import com.igorvd.chuckfacts.domain.jokes.repository.SearchHistoricRepository
import dagger.Module
import dagger.Provides

@Module
class SearchHistoricModule {

    @Provides
    fun providesSearchHistoricRepository(
        searchHistoricRepositoryImpl: SearchHistoricRepositoryImpl
    ): SearchHistoricRepository = searchHistoricRepositoryImpl
}