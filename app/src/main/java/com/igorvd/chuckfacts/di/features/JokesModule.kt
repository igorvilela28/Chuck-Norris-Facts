package com.igorvd.chuckfacts.di.features

import androidx.lifecycle.ViewModel
import com.igorvd.chuckfacts.data.jokes.repository.JokeRepositoryImpl
import com.igorvd.chuckfacts.di.core.ViewModelKey
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import com.igorvd.chuckfacts.features.jokes.JokesViewModel
import com.igorvd.chuckfacts.features.search.SearchJokeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
abstract class JokesModule {

    @Binds
    @IntoMap
    @ViewModelKey(JokesViewModel::class)
    abstract fun bindsJokesViewModel(jokesViewModel: JokesViewModel): ViewModel

}