package com.igorvd.chuckfacts.features.jokes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveJokesInteractor
import com.igorvd.chuckfacts.testutils.DUMMY_JOKES
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
    private lateinit var observerJokes: Observer<List<Joke>>

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        viewModel = JokesViewModel(retrieveJokesInteractor)

        viewModel.showProgressEvent.observeForever(observerShowProgress)
        viewModel.hideProgressEvent.observeForever(observerHideProgress)
        viewModel.jokes.observeForever(observerJokes)
    }

    @After
    fun tearDown() {
        viewModel.showProgressEvent.removeObserver(observerShowProgress)
        viewModel.hideProgressEvent.removeObserver(observerHideProgress)
        viewModel.jokes.removeObserver(observerJokes)
    }

    @Test
    fun `should retrieve jokes`() = runBlocking {

        val params = RetrieveJokesInteractor.Params("dev")
        coEvery { retrieveJokesInteractor.execute(params) } returns DUMMY_JOKES

        viewModel.retrieveJokes("dev")

        verifySequence {
            observerShowProgress.onChanged(null)
            observerJokes.onChanged(DUMMY_JOKES)
            observerHideProgress.onChanged(null)
        }
    }
}