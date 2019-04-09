package com.igorvd.chuckfacts.features.jokes

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.matcher.ViewMatchers.*
import com.igorvd.chuckfacts.R
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.Assert
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import com.igorvd.chuckfacts.features.search.SearchJokeActivity
import com.igorvd.chuckfacts.utils.*
import com.igorvd.chuckfacts.utils.matcher.CustomAssertions.Companion.hasItemCount
import kotlinx.android.synthetic.main.activity_jokes.*


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

    fun givenIOError() = apply {
        server.enqueueIOError("{}")
    }

    fun givenJokes500Response() = apply {
        server.enqueue500Response("{}")
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
        //TODO
    }

    fun thenJokesItemCount(count: Int) = apply {
        onView(withId(R.id.rvJokes))
            .check(hasItemCount(count))
    }



    //endregion

}