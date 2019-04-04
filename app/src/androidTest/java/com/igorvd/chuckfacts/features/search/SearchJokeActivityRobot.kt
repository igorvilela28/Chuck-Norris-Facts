package com.igorvd.chuckfacts.features.search

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.utils.AssetsLoader
import com.igorvd.chuckfacts.utils.enqueue200Response
import com.igorvd.chuckfacts.utils.enqueue500Response
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not

class SearchJokeActivityRobot(private val server: MockWebServer) {

    private val categories = listOf( "explicit", "dev", "movie", "food", "celebrity", "science",
        "sport", "political")

    fun launchActivity() = apply {
        ActivityScenario.launch(SearchJokeActivity::class.java)
    }


    fun givenCategories200Response() = apply {
        val response = AssetsLoader.loadAsset("categories_response_200.json")
        server.enqueue200Response(response)

    }

    fun givenCategories500Response() = apply {
        server.enqueue500Response("[]")
    }

    fun thenSearchEditTextIsDisplayed() = apply {
        onView(withId(R.id.etSearchJoke))
            .check(matches(isDisplayed()))
    }

    fun thenSuggestionLabelIsDisplayed() = apply {
        onView(withId(R.id.tvSuggestions))
            .check(matches(isDisplayed()))
    }

    fun thenSuggestionLabelIsNotDisplayed() = apply {
        onView(withId(R.id.tvSuggestions))
            .check(matches(not(isDisplayed())))
    }

    fun thenCategoriesAreDisplayed() = apply {

        for (category in categories) {
            onView(withText(category))
                .check(matches(isDisplayed()))
        }

    }

    fun thenCategoriesArentDisplayed() = apply {

        for (category in categories) {
            onView(withText(category))
                .check(doesNotExist())
        }

    }


}