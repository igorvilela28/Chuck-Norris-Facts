package com.igorvd.chuckfacts.di.features

import androidx.lifecycle.ViewModel
import com.igorvd.chuckfacts.di.core.ViewModelKey
import com.igorvd.chuckfacts.features.jokes.JokesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.FlowPreview


@FlowPreview
@Module
abstract class JokesModule {

    @Binds
    @IntoMap
    @ViewModelKey(JokesViewModel::class)
    abstract fun bindsJokesViewModel(jokesViewModel: JokesViewModel): ViewModel

}