package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import com.igorvd.chuckfacts.domain.testutils.DUMMY_JOKES
import com.igorvd.chuckfacts.domain.testutils.dummyJokesFlow
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@FlowPreview
class RetrieveJokesInteractorTest {

    private lateinit var retrieveJokesInteractor: RetrieveJokesInteractor

    @MockK
    private lateinit var repository: JokeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        retrieveJokesInteractor = RetrieveJokesInteractor(repository)
    }

    @Test
    fun `should retrieve jokes`() = runBlocking {

        coEvery { repository.retrieveJokes("infinite") } returns dummyJokesFlow()

        val jokes = retrieveJokesInteractor
            .execute(RetrieveJokesInteractor.Params("infinite")).single()

        assertEquals(DUMMY_JOKES, jokes)

        coVerifySequence { repository.retrieveJokes("infinite") }
    }

}