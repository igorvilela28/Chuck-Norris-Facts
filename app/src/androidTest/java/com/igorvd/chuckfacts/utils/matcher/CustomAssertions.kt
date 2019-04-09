package com.igorvd.chuckfacts.utils.matcher

import android.view.View
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.TypeSafeMatcher

class CustomAssertions {
    companion object {
        fun hasItemCount(count: Int): ViewAssertion {
            return RecyclerViewItemCountAssertion(count)
        }

        fun withDrawable(@DrawableRes drawableRes: Int): TypeSafeMatcher<View> {
            return DrawableMatcher(drawableRes)
        }
    }

    private class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {

        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            if (view !is RecyclerView) {
                throw IllegalStateException("The asserted view is not RecyclerView")
            }

            view.adapter?.let { adapter ->
                ViewMatchers.assertThat(
                    "RecyclerView item count",
                    adapter.itemCount,
                    CoreMatchers.equalTo(count)
                )
            } ?: throw IllegalStateException("No adapter is assigned to RecyclerView")
        }
    }
}