package com.igorvd.chuckfacts.features.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.interactor.AddQueryToSearchHistoricInteractor
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveCategoriesInteractor
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveJokesInteractor
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveSearchHistoricInteractor
import com.igorvd.chuckfacts.features.jokes.JokesViewModel
import com.igorvd.chuckfacts.testutils.DUMMY_CATEGORIES
import com.igorvd.chuckfacts.testutils.DUMMY_JOKES
import com.igorvd.chuckfacts.testutils.DUMMY_SEARCH_HISTORIC
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class SearchJokeViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchJokeViewModel
    @MockK
    private lateinit var retrieveCategoriesInteractor: RetrieveCategoriesInteractor
    @MockK
    private lateinit var retrieveSearchHistoricInteractor: RetrieveSearchHistoricInteractor
    @MockK
    private lateinit var addQueryToSearchHistoricInteractor: AddQueryToSearchHistoricInteractor

    @RelaxedMockK
    private lateinit var observerShowProgress: Observer<Void>
    @RelaxedMockK
    private lateinit var observerHideProgress: Observer<Void>
    @RelaxedMockK
    private lateinit var observerNetworkingError: Observer<Void>
    @RelaxedMockK
    private lateinit var observerHttpError: Observer<Void>

    @RelaxedMockK
    private lateinit var observerCategories: Observer<List<String>>
    @RelaxedMockK
    private lateinit var observerSearchHistoric: Observer<List<String>>
    @RelaxedMockK
    private lateinit var observerOnQueryAddedToHistoric: Observer<Void>


    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        viewModel = SearchJokeViewModel(
            retrieveCategoriesInteractor, retrieveSearchHistoricInteractor,
            addQueryToSearchHistoricInteractor
        )

        with(viewModel) {
            showProgressEvent.observeForever(observerShowProgress)
            hideProgressEvent.observeForever(observerHideProgress)
            categories.observeForever(observerCategories)
            searchHistoric.observeForever(observerSearchHistoric)
            onQueryAddedToHistoric.observeForever(observerOnQueryAddedToHistoric)
            showNetworkingError.observeForever(observerNetworkingError)
            showHttpError.observeForever(observerHttpError)
        }
    }

    @After
    fun tearDown() {
        with(viewModel) {
            showProgressEvent.removeObserver(observerShowProgress)
            hideProgressEvent.removeObserver(observerHideProgress)
            categories.removeObserver(observerCategories)
            searchHistoric.removeObserver(observerSearchHistoric)
            onQueryAddedToHistoric.removeObserver(observerOnQueryAddedToHistoric)
            showNetworkingError.removeObserver(observerNetworkingError)
            showHttpError.removeObserver(observerHttpError)
        }
    }

    @Test
    fun `should retrieve jokes categories`() = runBlocking {

        val params = RetrieveCategoriesInteractor.Params()
        coEvery { retrieveCategoriesInteractor.execute(params) } returns DUMMY_CATEGORIES

        viewModel.retrieveJokesCategories()

        verifySequence {
            observerShowProgress.onChanged(null)
            observerCategories.onChanged(DUMMY_CATEGORIES)
            observerHideProgress.onChanged(null)
        }

        verify {
            listOf(
                observerSearchHistoric, observerOnQueryAddedToHistoric,
                observerNetworkingError, observerHttpError
            ) wasNot Called
        }

    }

    @Test
    fun `should retrieve search historic`() = runBlocking {

        coEvery { retrieveSearchHistoricInteractor.execute(null) } returns DUMMY_SEARCH_HISTORIC

        viewModel.retrieveSearchHistoric()

        verify(exactly = 1) { observerSearchHistoric.onChanged(DUMMY_SEARCH_HISTORIC) }

        verify {
            listOf(
                observerCategories,
                observerOnQueryAddedToHistoric,
                observerNetworkingError,
                observerHttpError,
                observerShowProgress,
                observerHideProgress
            ) wasNot Called
        }

    }

    @Test
    fun `should add query to search historic`() = runBlocking {

        val params = AddQueryToSearchHistoricInteractor.Params("dev")
        coEvery { addQueryToSearchHistoricInteractor.execute(params) } returns true

        viewModel.addQueryToSearchHistoric("dev")

        verify(exactly = 1) { observerOnQueryAddedToHistoric.onChanged(null) }

        verify {
            listOf(
                observerCategories,
                observerSearchHistoric,
                observerNetworkingError,
                observerHttpError,
                observerShowProgress,
                observerHideProgress
            ) wasNot Called
        }

    }
}