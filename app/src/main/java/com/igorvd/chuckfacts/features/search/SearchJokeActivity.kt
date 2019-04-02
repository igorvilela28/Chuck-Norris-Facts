package com.igorvd.chuckfacts.features.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.utils.extensions.hideContent
import kotlinx.android.synthetic.main.search_toolbar.*
import androidx.transition.TransitionManager
import com.igorvd.chuckfacts.utils.extensions.showContent
import com.igorvd.chuckfacts.utils.transition.TransitionsFactory


class SearchJokeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_joke)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (savedInstanceState == null) {

            toolbar.hideContent()

            toolbar.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    toolbar.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    showSearch()
                }

            })

        }
    }

    private fun showSearch() {
        // use the TransitionManager to animate the changes of the Toolbar
        TransitionManager.beginDelayedTransition(toolbar, TransitionsFactory.fadeInTransition())
        toolbar.showContent()
    }
}
