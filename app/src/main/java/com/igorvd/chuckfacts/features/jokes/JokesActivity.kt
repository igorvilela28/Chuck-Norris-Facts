package com.igorvd.chuckfacts.features.jokes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.features.search.SearchJokeActivity
import kotlinx.android.synthetic.main.activity_jokes.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import com.igorvd.chuckfacts.utils.ViewModelFactory
import com.igorvd.chuckfacts.utils.extensions.hideContent
import com.igorvd.chuckfacts.utils.extensions.showContent
import com.igorvd.chuckfacts.utils.lifecycle.job
import com.igorvd.chuckfacts.utils.transition.TransitionsFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class JokesActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
    get() = lifecycle.job + Dispatchers.Main

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(JokesViewModel::class.java)
    }

    private val paramsHolder: Pair<LayoutParams, LayoutParams> by lazy {
        val originalParams = cvSearchBar.layoutParams as LayoutParams
        val newParams = LayoutParams(originalParams)
        newParams.setMargins(0, 0, 0, 0)
        originalParams to newParams
    }

    companion object {
        private const val RC_JOKE_QUERY = 9999
        const val EXTRA_JOKE_QUERY = "query"
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
    // region: LIFE CYCLE
    //**************************************************************************
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == RC_JOKE_QUERY && resultCode == Activity.RESULT_OK) {

            val query = data?.getStringExtra(EXTRA_JOKE_QUERY)
            Timber.d("received query: $query")

        }


        super.onActivityResult(requestCode, resultCode, data)
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
        startActivityForResult(intent, RC_JOKE_QUERY)
    }

    //endregion
}