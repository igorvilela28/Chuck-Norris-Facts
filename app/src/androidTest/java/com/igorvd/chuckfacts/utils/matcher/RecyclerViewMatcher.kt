package com.igorvd.chuckfacts.utils.matcher

import android.content.res.Resources
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    companion object {

        private const val NO_TARGET_VIEW_INDEX = -1
    }

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, NO_TARGET_VIEW_INDEX);
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            private var resources: Resources? = null
            private var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = recyclerViewId.toString()
                resources?.let {
                    try {
                        idDescription = it.getResourceName(recyclerViewId)
                    } catch (e: Exception) {
                        idDescription = "$recyclerViewId (resource name not found)"
                    }
                }
                description.appendText("with id: " + idDescription)
            }

            override fun matchesSafely(view: View): Boolean {
                if (childView == null) {
                    val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
                    if (recyclerView != null && recyclerView.id == recyclerViewId) {
                        childView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
                    } else {
                        return false
                    }
                }

                if (targetViewId == NO_TARGET_VIEW_INDEX) {
                    return view == childView
                } else {
                    childView?.let {
                        val targetView = it.findViewById<View>(targetViewId)
                        return view == targetView
                    } ?: return false
                }
            }
        }
    }

}