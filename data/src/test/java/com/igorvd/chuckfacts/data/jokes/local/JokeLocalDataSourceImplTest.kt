package com.igorvd.chuckfacts.data.jokes.local

import com.igorvd.chuckfacts.data.jokes.local.database.JokeDao
import com.igorvd.chuckfacts.data.jokes.local.database.JokeDatabase
import com.igorvd.chuckfacts.data.testutils.DUMMY_JOKES
import com.igorvd.chuckfacts.data.testutils.getDummyJokesEntities
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JokeLocalDataSourceImplTest {

    private lateinit var jokeLocalDataSourceImpl: JokeLocalDataSourceImpl

    @MockK
    private lateinit var jokeDb: JokeDatabase
    @MockK
    private lateinit var jokeDao: JokeDao

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        jokeLocalDataSourceImpl = JokeLocalDataSourceImpl(jokeDb)
        every { jokeDb.jokeDao() } returns jokeDao
    }

    @Test
    fun `should insert jokes`() = runBlocking {

        every { jokeDao.insertJokes(getDummyJokesEntities("dev")) } returns DUMMY_JOKES.map { it.id.toLong() }

        val isInsert = jokeLocalDataSourceImpl.insertJokes(DUMMY_JOKES, "dev")

        assertTrue(isInsert)
        verify { jokeDao.insertJokes(getDummyJokesEntities("dev")) }

    }

    @Test
    fun `should retrieve jokes`() = runBlocking {

        every { jokeDao.retrieveJokes("dev") } returns getDummyJokesEntities("dev")

        val jokes = jokeLocalDataSourceImpl.retrieveJokes("dev")

        assertEquals(DUMMY_JOKES, jokes)
        verify { jokeDao.retrieveJokes("dev") }

    }

    @Test
    fun `should retrieve random jokes`() = runBlocking {

        every { jokeDao.retrieveRandomJokes(10) } returns getDummyJokesEntities("dev")

        val jokes = jokeLocalDataSourceImpl.retrieveRandomJokes(10)

        assertEquals(DUMMY_JOKES, jokes)
        verify { jokeDao.retrieveRandomJokes(10) }

    }


}