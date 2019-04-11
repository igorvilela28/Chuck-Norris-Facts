package com.igorvd.chuckfacts.features.jokes

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.matcher.ViewMatchers.*
import okhttp3.mockwebserver.MockWebServer
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.igorvd.chuckfacts.features.search.SearchJokeActivity
import com.igorvd.chuckfacts.testutils.matcher.CustomAssertions.Companion.hasItemCount
import com.igorvd.chuckfacts.testutils.matcher.CustomMatchers.Companion.childViewAt
import com.igorvd.chuckfacts.testutils.matcher.CustomMatchers.Companion.withDrawable
import com.igorvd.chuckfacts.testutils.matcher.CustomMatchers.Companion.withFontSize
import com.igorvd.chuckfacts.testutils.matcher.CustomMatchers.Companion.withRecyclerViewChildAt
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.testutils.*
import org.hamcrest.Matchers.*


class JokesActivityRobot(private val server: MockWebServer) {

    lateinit var scenario: ActivityScenario<JokesActivity>

    fun launchActivity() = apply {
        scenario = ActivityScenario.launch(JokesActivity::class.java)
    }

    //region: given

    fun givenJokes200Response() = apply {
        val response = AssetsLoader.loadAsset("jokes_200_response.json")
        server.enqueue200Response(response)
    }

    fun givenJokesEmpty200Response() = apply {
        val response = AssetsLoader.loadAsset("jokes_200_empty_response.json")
        server.enqueue200Response(response)
    }

    fun givenIOError(amount: Int = 1) = apply {
        for (i in 0..amount) {
            server.enqueueIOError("{}")
        }
    }

    fun givenJokes500Response(amount: Int = 1) = apply {
        for (i in 0..amount) {
            server.enqueue500Response("{}")
        }
    }

    fun givenJokesOnLocalStorage(amount: Int, query: String) = apply {
        JokeTestDatabase.insertJokes(amount, query)
    }

    //endregion

    //region: when

    fun whenClickOnSearch() = apply {
        onView(withId(R.id.cvSearchBar))
            .perform(click())
    }

    fun whenClickOnTryAgain() = apply {
        onView(withId(R.id.btTryAgain))
            .perform(click())
    }

    fun whenClickOnShareUrlAtPosition(position: Int) = apply {

        onView(withRecyclerViewChildAt(R.id.rvJokes, R.id.btShare, position))
            .perform(click())

    }

    fun whenActivityResultWithQuery(query: String) = apply {

        val resultData = Intent().apply {
            putExtra(JokesActivity.EXTRA_JOKE_QUERY, query)
        }
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
        intending(hasComponent(SearchJokeActivity::class.java.name)).respondWith(result)
    }

    //endregion

    //region: then

    fun thenErrorLayoutIsDisplayed() = apply {
        onView(withId(R.id.errorLayout))
            .check(matches(isDisplayed()))
    }

    fun thenErrorLayoutIsNotDisplayed() = apply {
        onView(withId(R.id.errorLayout))
            .check(matches(not(isDisplayed())))
    }

    fun thenErrorMessageHasText(textRes: Int) = apply {
        onView(withId(R.id.tvErrorMessage))
            .check(matches(isDisplayed()))
            .check(matches(withText(textRes)))
    }

    fun thenTryAgainButtonIsDisplayed() = apply {
        onView(withId(R.id.btTryAgain))
            .check(matches(isDisplayed()))
    }

    fun thenTryAgainButtonIsNotDisplayed() = apply {
        onView(withId(R.id.btTryAgain))
            .check(matches(not(isDisplayed())))
    }

   fun thenErrorIconIsDisplayed(errorIconRes: Int) = apply {
       onView(withId(R.id.ivErrorIcon))
           .check(matches(isDisplayed()))
           .check(matches(withDrawable(errorIconRes)))
    }

    fun thenJokesItemCount(count: Int) = apply {
        onView(withId(R.id.rvJokes))
            .check(hasItemCount(count))
    }

    fun thenJokeAtPositionHasText(position: Int, text: String) = apply {

        onView(withRecyclerViewChildAt(R.id.rvJokes, R.id.tvJokeValue, position))
            .check(matches(withText(text)))
    }

    fun thenJokeAtPositionHasLargerFont(position: Int) = apply {

        lateinit var context: Context
        scenario.onActivity { context = it }
        val fontSize = context.resources.getDimension(R.dimen.text_large)

        onView(withRecyclerViewChildAt(R.id.rvJokes, R.id.tvJokeValue, position))
            .check(matches(withFontSize(fontSize)))

    }

    fun thenJokeAtPositionHasSmallerFont(position: Int) = apply {

        lateinit var context: Context
        scenario.onActivity { context = it }
        val fontSize = context.resources.getDimension(R.dimen.text_small)

        onView(withRecyclerViewChildAt(R.id.rvJokes, R.id.tvJokeValue, position))
            .check(matches(withFontSize(fontSize)))

    }

    fun thenJokeAtHasCategory(position: Int, category: String) = apply {

        onView(childViewAt(withRecyclerViewChildAt(R.id.rvJokes, R.id.chipGroupCategory, position), 0))
            .check(matches(withText(category)))

    }

    fun thenJokeUrlIsShared(url: String) {

        intended(allOf(hasAction(Intent.ACTION_CHOOSER), hasExtra(`is`(Intent.EXTRA_INTENT),
            allOf(hasAction(Intent.ACTION_SEND),hasExtra(Intent.EXTRA_TEXT, url)))))

    }

    //endregion

}