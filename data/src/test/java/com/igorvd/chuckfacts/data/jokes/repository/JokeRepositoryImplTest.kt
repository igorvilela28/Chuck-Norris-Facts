package com.igorvd.chuckfacts.data.jokes.repository

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

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        jokeRepositoryImpl = JokeRepositoryImpl(jokeRemoteDataSource)
    }


    @Test
    fun `should load jokes from remote data source`() = runBlocking {

        coEvery { jokeRemoteDataSource.getJokes("dev") } returns DUMMY_JOKES

        val jokes = jokeRepositoryImpl.retrieveJokes("dev")

        coVerify(exactly = 1) { jokeRemoteDataSource.getJokes("dev") }

    }


}