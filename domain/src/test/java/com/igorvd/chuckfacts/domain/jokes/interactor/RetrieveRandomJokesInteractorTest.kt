package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import com.igorvd.chuckfacts.domain.testutils.DUMMY_JOKES
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RetrieveRandomJokesInteractorTest {

    private lateinit var retrieveRandomJokesInteractor: RetrieveRandomJokesInteractor

    @MockK
    private lateinit var repository: JokeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        retrieveRandomJokesInteractor = RetrieveRandomJokesInteractor(repository)
    }

    @Test
    fun `should retrieve random jokes`() = runBlocking {

        coEvery { repository.retrieveRandomJokes(5) } returns DUMMY_JOKES

        val jokes = retrieveRandomJokesInteractor.execute(RetrieveRandomJokesInteractor.Params(5))

        coVerifySequence { repository.retrieveRandomJokes(5) }
    }


}