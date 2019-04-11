package com.igorvd.chuckfacts.data.jokes.remote

import com.igorvd.chuckfacts.data.network.requests.RequestMaker
import com.igorvd.chuckfacts.data.network.requests.RequestMakerImpl
import com.igorvd.chuckfacts.data.network.requests.CallRetryDelays
import com.igorvd.chuckfacts.data.testutils.DUMMY_CATEGORIES
import com.igorvd.chuckfacts.data.testutils.enqueue200Response
import com.igorvd.chuckfacts.data.testutils.loadJsonFromResources
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class JokeCategoryRemoteDataSourceImplTest : BaseRemoteTest() {

    private lateinit var jokeCategoryRemoteDataSourceImpl: JokeCategoryRemoteDataSourceImpl
    private val requestMaker: RequestMaker = RequestMakerImpl(CallRetryDelays(0, 0))

    @Before
    override fun setUp() {
        super.setUp()
        jokeCategoryRemoteDataSourceImpl = JokeCategoryRemoteDataSourceImpl(chuckNorrisApi, requestMaker)
    }

    @Test
    fun `should load categories`() = runBlocking {

        val categoriesResponse = loadJsonFromResources(
            javaClass.classLoader!!,
            "categories_response_200.json")
        server.enqueue200Response(categoriesResponse)

        val categories = jokeCategoryRemoteDataSourceImpl.retrieveAll()

        assertEquals(DUMMY_CATEGORIES, categories)

    }

}