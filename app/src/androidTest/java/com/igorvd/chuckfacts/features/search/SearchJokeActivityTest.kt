package com.igorvd.chuckfacts.features.search

import androidx.test.espresso.intent.Intents
import org.junit.runner.RunWith

import androidx.test.ext.junit.runners.AndroidJUnit4
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Before
import org.junit.Test
import com.igorvd.chuckfacts.utils.PreferencesUtils


@RunWith(AndroidJUnit4::class)
class SearchJokeActivityTest {

    lateinit var server: MockWebServer
    private lateinit var robot: SearchJokeActivityRobot

    @Before
    fun setUp() {
        Intents.init()
        server = MockWebServer()
        server.start(8080)
        robot = SearchJokeActivityRobot(server)
        PreferencesUtils.clearAllPreferences()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun shouldLoadCategories_WhenApiResponse200() {
        robot
            .givenCategories200Response()
            .launchActivity()
            .thenSuggestionLabelIsDisplayed()
            .thenCategoriesAreDisplayed()
    }

    @Test
    fun shouldNotLoadCategories_WhenApiResponse500() {
        robot
            .givenCategories500Response()
            .launchActivity()
            .thenSuggestionLabelIsNotDisplayed()
            .thenCategoriesArentDisplayed()
    }

    @Test
    fun shouldFinishWithResult_WhenClickOnCategory() {
        robot
            .givenCategories200Response()
            .launchActivity()
            .whenClickOnCategory(robot.categories.first())
            .thenActivityResultWithQuery(robot.categories.first())

    }

    @Test
    fun shouldFinishWithQueryResult_WhenTypeQuery() {
        robot
            .givenCategories200Response()
            .launchActivity()
            .whenTypeQuery("dev")
            .whenSearchImeClicked()
            .thenActivityResultWithQuery("dev")
    }

    @Test
    fun shouldShowQueryEmptyError_WhenClickOnSearchIme() {
        robot
            .givenCategories200Response()
            .launchActivity()
            .whenSearchImeClicked()
            .thenTypeQueryErrorIsDisplayed()

    }
}