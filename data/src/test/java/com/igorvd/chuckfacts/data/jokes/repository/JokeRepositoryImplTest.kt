package com.igorvd.chuckfacts.data.jokes.repository

import com.igorvd.chuckfacts.data.jokes.local.JokeLocalDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSource
import com.igorvd.chuckfacts.data.testutils.DUMMY_JOKES
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class JokeRepositoryImplTest {

    private lateinit var jokeRepositoryImpl: JokeRepositoryImpl
    @MockK
    private lateinit var jokeRemoteDataSource: JokeRemoteDataSource
    @MockK
    private lateinit var jokeLocalDataSource: JokeLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        jokeRepositoryImpl = JokeRepositoryImpl(jokeRemoteDataSource, jokeLocalDataSource)
    }

    @Test
    fun `should load jokes from local data source`() = runBlocking {

        coEvery { jokeLocalDataSource.retrieveJokes("dev") } returns DUMMY_JOKES

        val jokes = jokeRepositoryImpl.retrieveJokes("dev")

        assertEquals(DUMMY_JOKES, jokes)
        coVerify(exactly = 1)  { jokeLocalDataSource.retrieveJokes("dev") }
        verify { jokeRemoteDataSource wasNot Called }
    }

    @Test
    fun `should load jokes from remote data source`() = runBlocking {

        coEvery { jokeLocalDataSource.retrieveJokes("dev") } returns emptyList()
        coEvery { jokeRemoteDataSource.getJokes("dev") } returns DUMMY_JOKES
        coEvery { jokeLocalDataSource.insertJokes(DUMMY_JOKES, "dev") } returns true

        val jokes = jokeRepositoryImpl.retrieveJokes("dev")

        assertEquals(DUMMY_JOKES, jokes)
        coVerifySequence {
            jokeLocalDataSource.retrieveJokes("dev")
            jokeRemoteDataSource.getJokes("dev")
            jokeLocalDataSource.insertJokes(DUMMY_JOKES, "dev")
        }

    }

    @Test
    fun `should load random jokes`() = runBlocking {

        coEvery { jokeLocalDataSource.retrieveRandomJokes(10) } returns DUMMY_JOKES

        val jokes = jokeRepositoryImpl.retrieveRandomJokes(10)

        assertEquals(DUMMY_JOKES, jokes)
        coVerify(exactly = 1) { jokeLocalDataSource.retrieveRandomJokes(10) }

    }


}