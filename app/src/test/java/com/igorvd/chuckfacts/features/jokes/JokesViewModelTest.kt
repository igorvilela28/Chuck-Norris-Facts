package com.igorvd.chuckfacts.features.jokes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.igorvd.chuckfacts.domain.exceptions.MyHttpErrorException
import com.igorvd.chuckfacts.domain.exceptions.MyIOException
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveJokesInteractor
import com.igorvd.chuckfacts.features.jokes.model.JokeView
import com.igorvd.chuckfacts.testutils.DUMMY_JOKES
import com.igorvd.chuckfacts.testutils.DUMMY_JOKESVIEW
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.net.SocketTimeoutException

class JokesViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: JokesViewModel
    @MockK
    private lateinit var retrieveJokesInteractor: RetrieveJokesInteractor
    @RelaxedMockK
    private lateinit var observerShowProgress: Observer<Void>
    @RelaxedMockK
    private lateinit var observerHideProgress: Observer<Void>
    @RelaxedMockK
    private lateinit var observerJokes: Observer<List<JokeView>>
    @RelaxedMockK
    private lateinit var observerEmptyJokesResult: Observer<Void>
    @RelaxedMockK
    private lateinit var observerNetworkingError: Observer<Void>
    @RelaxedMockK
    private lateinit var observerHttpError: Observer<Void>

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        viewModel = JokesViewModel(retrieveJokesInteractor)
        with (viewModel) {
            showProgressEvent.observeForever(observerShowProgress)
            hideProgressEvent.observeForever(observerHideProgress)
            jokes.observeForever(observerJokes)
            showEmptyJokesResult.observeForever(observerEmptyJokesResult)
            showNetworkingError.observeForever(observerNetworkingError)
            showHttpError.observeForever(observerHttpError)
        }
    }

    @After
    fun tearDown() {
        with (viewModel) {
            showProgressEvent.removeObserver(observerShowProgress)
            hideProgressEvent.removeObserver(observerHideProgress)
            jokes.removeObserver(observerJokes)
            showEmptyJokesResult.removeObserver(observerEmptyJokesResult)
            showNetworkingError.removeObserver(observerNetworkingError)
            showHttpError.removeObserver(observerHttpError)
        }
    }

    @Test
    fun `should retrieve jokes when result is not empty`() = runBlocking {

        val params = RetrieveJokesInteractor.Params("dev")
        coEvery { retrieveJokesInteractor.execute(params) } returns DUMMY_JOKES

        viewModel.retrieveJokes("dev")

        verifySequence {
            observerShowProgress.onChanged(null)
            observerJokes.onChanged(DUMMY_JOKESVIEW)
            observerHideProgress.onChanged(null)
        }

        verify { observerEmptyJokesResult wasNot Called }
    }

    @Test
    fun `should notify about empty result`() = runBlocking {

        val params = RetrieveJokesInteractor.Params("dev")
        coEvery { retrieveJokesInteractor.execute(params) } returns emptyList()

        viewModel.retrieveJokes("dev")

        verifySequence {
            observerShowProgress.onChanged(null)
            observerEmptyJokesResult.onChanged(null)
            observerHideProgress.onChanged(null)
        }

        verify { listOf(observerJokes, observerNetworkingError, observerHttpError) wasNot Called }
    }

    @Test
    fun `should notify about networking error when retrieving jokes`() = runBlocking {

        val params = RetrieveJokesInteractor.Params("dev")
        coEvery { retrieveJokesInteractor.execute(params) } throws MyIOException("io error", SocketTimeoutException())

        viewModel.retrieveJokes("dev")

        verifySequence {
            observerShowProgress.onChanged(null)
            observerNetworkingError.onChanged(null)
            observerHideProgress.onChanged(null)
        }

        verify { listOf(observerJokes, observerEmptyJokesResult, observerHttpError) wasNot Called }
    }

    @Test
    fun `should notify about http error when retrieving jokes`() = runBlocking {

        val params = RetrieveJokesInteractor.Params("dev")
        coEvery { retrieveJokesInteractor.execute(params) } throws MyHttpErrorException
            .HttpServerErrorException("server unvailable", 500)

        viewModel.retrieveJokes("dev")

        verifySequence {
            observerShowProgress.onChanged(null)
            observerHttpError.onChanged(null)
            observerHideProgress.onChanged(null)
        }

        verify { listOf(observerJokes, observerEmptyJokesResult, observerNetworkingError) wasNot Called }
    }
}