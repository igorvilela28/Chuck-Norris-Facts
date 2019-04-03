package com.igorvd.chuckfacts.features.jokes

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.features.search.SearchJokeActivity
import kotlinx.android.synthetic.main.activity_jokes.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.transition.TransitionManager
import com.igorvd.chuckfacts.utils.extensions.hideContent
import com.igorvd.chuckfacts.utils.extensions.showContent
import com.igorvd.chuckfacts.utils.transition.TransitionsFactory

class JokesActivity : AppCompatActivity() {

    private val paramsHolder: Pair<LayoutParams, LayoutParams> by lazy {
        val originalParams = cvSearchBar.layoutParams as LayoutParams
        val newParams = LayoutParams(originalParams)
        newParams.setMargins(0, 0, 0, 0)
        originalParams to newParams
    }

    //**************************************************************************
    // region: LIFE CYCLE
    //**************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        setupViews()
    }

    override fun onResume() {
        super.onResume()
        setOriginalBarProperties()
    }

    //endregion

    //**************************************************************************
    // region: INNER METHODS
    //**************************************************************************

    private fun setupViews() {
        cvSearchBar.setOnClickListener {
            val transition = TransitionsFactory.changeBoundsWithActionOnEnd(action = ::startSearchActivity)
            TransitionManager.beginDelayedTransition(cvSearchBar as CardView, transition)
            cvSearchBar.layoutParams = paramsHolder.second
            ((cvSearchBar as CardView).getChildAt(0) as ViewGroup).hideContent()
        }
    }

    private fun setOriginalBarProperties() {
        cvSearchBar.layoutParams = paramsHolder.first
        ((cvSearchBar as CardView).getChildAt(0) as ViewGroup).showContent()
    }

    private fun startSearchActivity() {
        val intent = SearchJokeActivity.newIntent(this)
        startActivity(intent)
    }

    //endregion
}