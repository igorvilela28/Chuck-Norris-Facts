package com.igorvd.chuckfacts.features.search

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.features.jokes.JokesActivity
import com.igorvd.chuckfacts.utils.AssetsLoader
import com.igorvd.chuckfacts.utils.enqueue200Response
import com.igorvd.chuckfacts.utils.enqueue500Response
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.Assert
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import com.igorvd.chuckfacts.utils.PreferencesUtils
import com.igorvd.chuckfacts.utils.matcher.CustomAssertions
import com.igorvd.chuckfacts.utils.matcher.CustomAssertions.Companion.hasItemCount


class SearchJokeActivityRobot(private val server: MockWebServer) {

    val categories = listOf(
        "explicit", "dev", "movie", "food", "celebrity", "science",
        "sport", "political"
    )

    val historic = listOf("explicit", "dev", "movie", "food", "celebrity", "science",
        "sport", "political")

    lateinit var scenario: ActivityScenario<SearchJokeActivity>

    fun launchActivity() = apply {
        scenario = ActivityScenario.launch(SearchJokeActivity::class.java)
    }

    //region: given

    fun givenCategories200Response() = apply {
        val response = AssetsLoader.loadAsset("categories_response_200.json")
        server.enqueue200Response(response)

    }

    fun givenCategories500Response() = apply {
        server.enqueue500Response("[]")
    }

    fun givenSearchHistoric(historicItemsCount: Int = 5) = apply {
        PreferencesUtils.putSearchHistoric(historic.take(historicItemsCount))
    }

    //endregion

    //region: when

    fun whenClickOnCategory(category: String) = apply {
        onView(withText(category))
            .perform(click())
    }

    fun whenTypeQuery(query: String) = apply {
        onView(withId(R.id.etSearchJoke))
            .perform(typeText(query))
    }

    fun whenSearchImeClicked() = apply {
        onView(withId(R.id.etSearchJoke))
            .perform(pressImeActionButton())
    }

    fun whenClickOnPastSearchItem(position: Int) = apply {

       onView(withId(R.id.rvPastSearches))
            .perform(actionOnItemAtPosition<SearchHistoricAdapter.MyViewHolder>(position, click()))


    }

    //endregion

    //region: then

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

    fun thenPastSearchLabelIsDisplayed() = apply {
        onView(withId(R.id.tvPastSearches))
            .check(matches(isDisplayed()))
    }

    fun thenPastSearchLabelIsNotDisplayed() = apply {
        onView(withId(R.id.tvPastSearches))
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

    fun thenTypeQueryErrorIsDisplayed() = apply {
        lateinit var activity: Activity
        scenario.onActivity { activity = it }

        onView(withText(R.string.search_error_type_query))
            .inRoot(withDecorView(not(activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    fun thenPastSearchesItemCount(count: Int) = apply {
        onView(withId(R.id.rvPastSearches))
            .check(hasItemCount(count))
    }


    fun thenActivityResultWithQuery(query: String) = apply {
        val result = scenario.result
        Assert.assertEquals(Activity.RESULT_OK, result.resultCode)
        Assert.assertEquals(
            query,
            result.resultData.getStringExtra(JokesActivity.EXTRA_JOKE_QUERY)
        )
    }

    //endregion

}