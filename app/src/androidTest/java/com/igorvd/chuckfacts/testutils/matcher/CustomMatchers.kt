package com.igorvd.chuckfacts.testutils.matcher

import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class CustomMatchers {

    companion object {

        fun withDrawable(@DrawableRes drawableRes: Int): TypeSafeMatcher<View> {
            return DrawableMatcher(drawableRes)
        }

        fun withFontSize(fontSize: Float): Matcher<View> {
            return FontSizeMatcher(fontSize)
        }

        fun withRecyclerViewChildAt(
            @IdRes recyclerViewRes: Int,
            @IdRes childId: Int,
            position: Int
        ): Matcher<View> {
            return RecyclerViewMatcher(recyclerViewRes)
                .atPositionOnView(position, childId)
        }

        fun childViewAt(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description) {
                    description.appendText("position $childPosition of parent ")
                    parentMatcher.describeTo(description)
                }

                override fun matchesSafely(view: View): Boolean {
                    if (view.getParent() !is ViewGroup) return false
                    val parent = view.getParent() as ViewGroup

                    return (parentMatcher.matches(parent)
                            && parent.childCount > childPosition
                            && parent.getChildAt(childPosition) == view)
                }
            }
        }

    }


}