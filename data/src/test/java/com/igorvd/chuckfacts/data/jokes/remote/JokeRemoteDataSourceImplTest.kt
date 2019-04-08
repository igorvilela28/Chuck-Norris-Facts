package com.igorvd.chuckfacts.data.jokes.remote

import android.content.Context
import com.igorvd.chuckfacts.data.R
import com.igorvd.chuckfacts.data.network.requests.RequestMakerImpl
import com.igorvd.chuckfacts.data.testutils.enqueue200Response
import com.igorvd.chuckfacts.data.testutils.loadJsonFromResources
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class JokeRemoteDataSourceImplTest : BaseRemoteTest() {

    private lateinit var jokeRemoteDataSourceImpl: JokeRemoteDataSourceImpl
    @MockK
    private lateinit var context: Context
    private val requestMaker = RequestMakerImpl()

    @Before
    override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        jokeRemoteDataSourceImpl = JokeRemoteDataSourceImpl(context, chuckNorrisApi, requestMaker)
    }

    @Test
    fun `should retrieve jokes and add default category when a joke doesnt have one`() = runBlocking {

        val jokesResponse = loadJsonFromResources(
            javaClass.classLoader!!,
            "jokes_response_200.json"
        )
        server.enqueue200Response(jokesResponse)
        every { context.getString(R.string.default_category) } returns "Uncategorized"

        val jokes = jokeRemoteDataSourceImpl.getJokes("dev")

        val firstJoke = jokes[0]
        assertTrue(firstJoke.categories.size == 1)
        assertEquals("UNCATEGORIZED", firstJoke.categories.first())
        assertEquals("kscxjliptieuza9q5i5azg", firstJoke.id)
        assertEquals(
            "https://api.chucknorris.io/jokes/kscxjliptieuza9q5i5azg",
            firstJoke.url
        )
        assertEquals(
            "TNT was originally developed by Chuck Norris to cure indigestion.",
            firstJoke.value
        )

        verify (exactly = 1) { context.getString(R.string.default_category) }

    }
}