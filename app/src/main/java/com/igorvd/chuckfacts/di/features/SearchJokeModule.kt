package com.igorvd.chuckfacts.di.features

import androidx.lifecycle.ViewModel
import com.igorvd.chuckfacts.di.core.ViewModelKey
import com.igorvd.chuckfacts.features.search.SearchJokeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class SearchJokeModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchJokeViewModel::class)
    abstract fun bindsSearchJokeViewModel(searchJokeViewModel: SearchJokeViewModel): ViewModel

}