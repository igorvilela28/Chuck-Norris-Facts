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
import com.igorvd.chuckfacts.utils.extensions.*
import com.igorvd.chuckfacts.utils.lifecycle.job
import com.igorvd.chuckfacts.utils.transition.TransitionsFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    private val adapter by lazy {
        JokesAdapter(this) {

        }
    }

    companion object {
        private const val RC_JOKE_QUERY = 9999
        const val EXTRA_JOKE_QUERY = "query"
    }

    //**************************************************************************
    // region: LIFE CYCLE
    //**************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        setupViews()
        setupObservers()

    }

    private fun setupObservers() = with(viewModel) {

        showProgressEvent.observeNullable(this@JokesActivity) {
            errorLayout.isVisible = false
            progressBarContainer.isVisible = true
            adapter.submitList(emptyList())
        }

        hideProgressEvent.observeNullable(this@JokesActivity) {
            progressBarContainer.isVisible = false
        }

        showEmptyJokesResult.observeNullable(this@JokesActivity) {
            errorLayout.isVisible = true
            btTryAgain.isVisible = false
            ivErrorIcon.setImageResource(R.drawable.empty_results)
            tvErrorMessage.setText(R.string.jokes_no_results)
        }

        showNetworkingError.observeNullable(this@JokesActivity) {
            errorLayout.isVisible = true
            btTryAgain.isVisible = true
            ivErrorIcon.setImageResource(R.drawable.error_networking)
            tvErrorMessage.setText(R.string.jokes_error_network)
        }

        showHttpError.observeNullable(this@JokesActivity) {
            errorLayout.isVisible = true
            btTryAgain.isVisible = true
            ivErrorIcon.setImageResource(R.drawable.error_server)
            tvErrorMessage.setText(R.string.jokes_error_server)
        }

        jokes.observeNotNull(this@JokesActivity) {
            adapter.submitList(it)
        }
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
            retrieveJokes(query)

        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    //endregion

    //**************************************************************************
    // region: INNER METHODS
    //**************************************************************************

    private fun setupViews() {
        cvSearchBar.setOnClickListener {
            val transition =
                TransitionsFactory.changeBoundsWithActionOnEnd(action = ::startSearchActivity)
            TransitionManager.beginDelayedTransition(cvSearchBar as CardView, transition)
            cvSearchBar.layoutParams = paramsHolder.second
            ((cvSearchBar as CardView).getChildAt(0) as ViewGroup).hideContent()
        }

        rvJokes.setup(context = this, adapter = adapter)

        btTryAgain.setOnClickListener { retrieveJokes(viewModel.lastQuery) }
    }

    private fun setOriginalBarProperties() {
        cvSearchBar.layoutParams = paramsHolder.first
        ((cvSearchBar as CardView).getChildAt(0) as ViewGroup).showContent()
    }

    private fun startSearchActivity() {
        val intent = SearchJokeActivity.newIntent(this)
        startActivityForResult(intent, RC_JOKE_QUERY)
    }

    private fun retrieveJokes(query: String?) {
        query?.let {
            launch {
                viewModel.retrieveJokes(query)
            }
        }
    }

    //endregion
}