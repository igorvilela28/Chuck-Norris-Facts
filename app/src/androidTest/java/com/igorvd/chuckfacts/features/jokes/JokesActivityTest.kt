package com.igorvd.chuckfacts.features.jokes

import androidx.test.espresso.intent.Intents
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.utils.PreferencesUtils
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class JokesActivityTest {

    lateinit var server: MockWebServer
    private lateinit var robot: JokesActivityRobot

    @Before
    fun setUp() {
        Intents.init()
        server = MockWebServer()
        server.start(8080)
        robot = JokesActivityRobot(server)
        PreferencesUtils.clearAllPreferences()
    }

    @After
    fun tearDown() {
        server.shutdown()
        Intents.release()
    }

    @Test
    fun shouldShowJokesResult_WhenApiResponse200() {

        robot
            .givenJokes200Response()
            .launchActivity()
            .whenActivityResultWithQuery("dev")
            .whenClickOnSearch()
            .thenErrorLayoutIsNotDisplayed()
            .thenJokesItemCount(2)

    }

    @Test
    fun shouldShowJokesEmptyResult_WhenApiResponseEmpty200() {

        robot
            .givenJokesEmpty200Response()
            .launchActivity()
            .whenActivityResultWithQuery("dev")
            .whenClickOnSearch()
            .thenJokesItemCount(0)
            .thenErrorLayoutIsDisplayed()
            .thenErrorIconIsDisplayed(R.drawable.empty_results)
            .thenErrorMessageHasText(R.string.jokes_no_results)
            .thenTryAgainButtonIsNotDisplayed()
    }

    @Test
    fun shouldShowHttpError_WhenApiResponse500() {

        robot
            .givenJokes500Response()
            .launchActivity()
            .whenActivityResultWithQuery("dev")
            .whenClickOnSearch()
            .thenJokesItemCount(0)
            .thenErrorLayoutIsDisplayed()
            .thenErrorIconIsDisplayed(R.drawable.error_server)
            .thenErrorMessageHasText(R.string.jokes_error_server)
            .thenTryAgainButtonIsDisplayed()
    }

    @Test
    fun shouldShowNetworkError_WhenIOException() {

        robot
            .givenIOError()
            .launchActivity()
            .whenActivityResultWithQuery("dev")
            .whenClickOnSearch()
            .thenJokesItemCount(0)
            .thenErrorLayoutIsDisplayed()
            .thenErrorIconIsDisplayed(R.drawable.error_networking)
            .thenErrorMessageHasText(R.string.jokes_error_network)
            .thenTryAgainButtonIsDisplayed()
    }

    @Test
    fun shouldRetrieveJokes_WhenClickOnTryAgain() {

        robot
            .givenIOError()
            .givenJokes200Response()
            .launchActivity()
            .whenActivityResultWithQuery("dev")
            .whenClickOnSearch()
            .whenClickOnTryAgain()
            .thenJokesItemCount(2)
            .thenErrorLayoutIsNotDisplayed()
    }



}