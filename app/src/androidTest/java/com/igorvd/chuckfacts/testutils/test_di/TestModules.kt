package com.igorvd.chuckfacts.testutils.test_di

import androidx.room.Room
import com.igorvd.chuckfacts.data.jokes.local.*
import com.igorvd.chuckfacts.data.jokes.local.database.JokeDatabase
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.remote.api.ChuckNorrisApi
import com.igorvd.chuckfacts.data.jokes.repository.JokeCategoryRepositoryImpl
import com.igorvd.chuckfacts.data.jokes.repository.JokeRepositoryImpl
import com.igorvd.chuckfacts.data.jokes.repository.SearchHistoricRepositoryImpl
import com.igorvd.chuckfacts.data.network.ApiClientBuilder
import com.igorvd.chuckfacts.data.network.requests.CallRetryDelays
import com.igorvd.chuckfacts.data.network.requests.RequestMaker
import com.igorvd.chuckfacts.data.network.requests.RequestMakerImpl
import com.igorvd.chuckfacts.domain.jokes.interactor.*
import com.igorvd.chuckfacts.domain.jokes.repository.JokeCategoryRepository
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import com.igorvd.chuckfacts.domain.jokes.repository.SearchHistoricRepository
import com.igorvd.chuckfacts.features.jokes.JokesViewModel
import com.igorvd.chuckfacts.features.search.SearchJokeViewModel
import com.igorvd.chuckfacts.testutils.JokeTestDatabase
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val MOCK_SERVER_URL = "http://localhost:8080/"

val networkModule = module {

    single {
        ApiClientBuilder.createService(
                ChuckNorrisApi::class.java,
                MOCK_SERVER_URL
        )
    }

    single {
        RequestMakerImpl(get()) as RequestMaker
    }

    single {
        CallRetryDelays(0L, 0L)
    }
}

val dataSourceModule = module {
    factory {
        JokeCategoryRemoteDataSourceImpl(get(), get()) as JokeCategoryRemoteDataSource
    }

    factory {
        JokeRemoteDataSourceImpl(get(), get(), get()) as JokeRemoteDataSource
    }

    factory {
        JokeLocalDataSourceImpl(get()) as JokeLocalDataSource
    }

    factory {
        JokeCategoryLocalDataSourceImpl(get()) as JokeCategoryLocalDataSource
    }
}

@FlowPreview
val repositoryModule = module {
    factory {
        JokeCategoryRepositoryImpl(get(), get()) as JokeCategoryRepository
    }

    factory {
        JokeRepositoryImpl(get(), get()) as JokeRepository
    }

    factory {
        SearchHistoricRepositoryImpl(get()) as SearchHistoricRepository
    }
}


val databaseModule = module {
    single {
        JokeTestDatabase.TEST_JOKES_DB
    }
}

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

@FlowPreview
val viewModule = module {
    viewModel { JokesViewModel(get(), get()) }
    viewModel { SearchJokeViewModel(get(), get(), get()) }
}