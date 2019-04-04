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

    /*@get:Rule
    val activityRule = ActivityTestRule(SearchJokeActivity::class.java, false, false)*/

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
}