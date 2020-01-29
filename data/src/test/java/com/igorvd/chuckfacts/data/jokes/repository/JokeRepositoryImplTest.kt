package com.igorvd.chuckfacts.data.jokes.repository

import com.igorvd.chuckfacts.data.jokes.local.JokeLocalDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSource
import com.igorvd.chuckfacts.data.testutils.DUMMY_JOKES
import com.igorvd.chuckfacts.domain.exceptions.MyIOException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException

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
    fun `should emit jokes from local and remote data source`() = runBlocking {

        val localJokes = listOf(DUMMY_JOKES.first())
        val remoteJokes = DUMMY_JOKES.drop(1)

        coEvery { jokeLocalDataSource.retrieveJokes("dev") } returns localJokes
        coEvery { jokeRemoteDataSource.getJokes("dev") } returns DUMMY_JOKES
        coEvery { jokeLocalDataSource.insertJokes(remoteJokes, "dev") } returns true


        val jokesFlow = jokeRepositoryImpl.retrieveJokes("dev")

        var currentCollected = 0
        jokesFlow.collect { jokes ->
            currentCollected++
            if (currentCollected == 1) {
                assertEquals(localJokes, jokes)
            } else if (currentCollected == 2) {
                assertEquals(remoteJokes, jokes)
            }
        }

        assertTrue(currentCollected == 2)

        coVerifySequence {
            jokeLocalDataSource.retrieveJokes("dev")
            jokeRemoteDataSource.getJokes("dev")
            jokeLocalDataSource.insertJokes(remoteJokes, "dev")
        }
    }

    @Test
    fun `should not throw exception when has local Jokes`() = runBlocking {

        coEvery { jokeLocalDataSource.retrieveJokes("dev") } returns DUMMY_JOKES
        coEvery { jokeRemoteDataSource.getJokes("dev") } throws MyIOException("io error", SocketTimeoutException())

        val jokesFlow = jokeRepositoryImpl.retrieveJokes("dev")

        val jokes = jokesFlow.single()
        assertEquals(DUMMY_JOKES, jokes)

        coVerifySequence {
            jokeLocalDataSource.retrieveJokes("dev")
            jokeRemoteDataSource.getJokes("dev")
        }
    }

    @Test(expected = MyIOException::class)
    fun `should throw exception when local Jokes is empty and remote source throws`() = runBlocking {

        coEvery { jokeLocalDataSource.retrieveJokes("dev") } returns emptyList()
        coEvery { jokeRemoteDataSource.getJokes("dev") } throws MyIOException("io error", SocketTimeoutException())

        val jokes = jokeRepositoryImpl.retrieveJokes("dev").single()
    }

    @Test
    fun `should emit jokes only from remote data source`() = runBlocking {

        coEvery { jokeLocalDataSource.retrieveJokes("dev") } returns emptyList()
        coEvery { jokeRemoteDataSource.getJokes("dev") } returns DUMMY_JOKES
        coEvery { jokeLocalDataSource.insertJokes(DUMMY_JOKES, "dev") } returns true

        val jokesFlow = jokeRepositoryImpl.retrieveJokes("dev")

        val jokes = jokesFlow.single()
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