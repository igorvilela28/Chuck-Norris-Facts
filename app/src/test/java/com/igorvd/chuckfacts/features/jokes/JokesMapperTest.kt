package com.igorvd.chuckfacts.features.jokes

import com.igorvd.chuckfacts.testutils.DUMMY_JOKES
import com.igorvd.chuckfacts.testutils.DUMMY_JOKESVIEW
import org.junit.Assert.*
import org.junit.Test

class JokesMapperTest {

    @Test
    fun `should map joke to JokeView when text value length is less than 80`() {
        val expectedJokeView = DUMMY_JOKESVIEW.first()
        val jokeView = JokesMapper.jokeToJokeView(DUMMY_JOKES.first())
        assertEquals(expectedJokeView, jokeView)
    }

    @Test
    fun `should map joke to JokeView when text value length is more than 80`() {
        val expectedJokeView = DUMMY_JOKESVIEW.last()
        val jokeView = JokesMapper.jokeToJokeView(DUMMY_JOKES.last())
        assertEquals(expectedJokeView, jokeView)
    }
}