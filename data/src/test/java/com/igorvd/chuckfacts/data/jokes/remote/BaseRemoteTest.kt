package com.igorvd.chuckfacts.data.jokes.remote

import com.igorvd.chuckfacts.data.jokes.remote.api.ChuckNorrisApi
import com.igorvd.chuckfacts.data.network.ApiClientBuilder
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

abstract class BaseRemoteTest {

    protected lateinit var server: MockWebServer
    protected lateinit var chuckNorrisApi: ChuckNorrisApi

    @Before
    open fun setUp() {
        server = MockWebServer()
        server.start()
        val url = server.url("/").toString()
        chuckNorrisApi = ApiClientBuilder.createService(ChuckNorrisApi::class.java, url)
    }

    @After
    open fun tearDown() {
        server.shutdown()
    }
}