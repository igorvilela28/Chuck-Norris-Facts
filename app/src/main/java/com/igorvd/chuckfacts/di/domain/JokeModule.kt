package com.igorvd.chuckfacts.di.domain

import com.igorvd.chuckfacts.data.jokes.repository.JokeRepositoryImpl
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.FlowPreview

@Module
class JokeModule {

    @Provides
    fun providesJokeRepository(
        jokeRepositoryImpl: JokeRepositoryImpl
    ): JokeRepository = jokeRepositoryImpl
}