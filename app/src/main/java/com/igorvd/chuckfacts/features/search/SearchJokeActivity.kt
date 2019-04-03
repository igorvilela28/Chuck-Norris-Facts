package com.igorvd.chuckfacts.features.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.core.app.NavUtils
import androidx.transition.TransitionManager
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.utils.extensions.hideContent
import com.igorvd.chuckfacts.utils.transition.TransitionsFactory
import kotlinx.android.synthetic.main.activity_search_joke.*

class SearchJokeActivity : AppCompatActivity() {

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SearchJokeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            }
            return intent
        }

    }

    //**************************************************************************
    // region: LIFE CYCLE
    //**************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_joke)

        setupViews()

    }

    //endregion

    //**************************************************************************
    // region: INNER METHODS
    //**************************************************************************

    private fun setupViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        toolbar.setNavigationOnClickListener {
            animateToolbar()
        }
    }

    private fun animateToolbar() {

        val transition = TransitionsFactory.fadeOutTransitionWithActionOnEnd(action = ::navigateUp)
        TransitionManager.beginDelayedTransition(toolbar, transition)

        (toolbar.layoutParams as LayoutParams).apply {
            val dimen = resources.getDimension(R.dimen.margin_padding_medium).toInt()
            setMargins(dimen, dimen, dimen, 0)
        }
        toolbar.hideContent()
    }

    private fun navigateUp() {
        NavUtils.navigateUpFromSameTask(this)
        overridePendingTransition(0, 0)
    }

    //endregion
}
