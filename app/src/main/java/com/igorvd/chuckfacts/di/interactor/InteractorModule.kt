package com.igorvd.chuckfacts.di.interactor

import com.igorvd.chuckfacts.domain.jokes.interactor.*
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module


@FlowPreview
val interactorModule = module {
    factory {
        RetrieveJokesInteractor(get())
    }

    factory {
        RetrieveRandomJokesInteractor(get())
    }

    factory {
        RetrieveCategoriesInteractor(get())
    }

    factory {
        RetrieveSearchHistoricInteractor(get())
    }

    factory {
        AddQueryToSearchHistoricInteractor(get())
    }
}