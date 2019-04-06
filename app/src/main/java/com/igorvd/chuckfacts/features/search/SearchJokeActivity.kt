package com.igorvd.chuckfacts.features.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.core.app.NavUtils
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.features.jokes.JokesActivity
import com.igorvd.chuckfacts.utils.ViewModelFactory
import com.igorvd.chuckfacts.utils.extensions.*
import com.igorvd.chuckfacts.utils.lifecycle.job
import com.igorvd.chuckfacts.utils.transition.TransitionsFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_joke.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchJokeActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = lifecycle.job + Dispatchers.Main

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchJokeViewModel::class.java)
    }

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
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_joke)
        setupViews()
        setupObservers()
        loadCategories()
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
            finishAnimatingToolbar()
        }

        etSearchJoke.setOnEditorActionListener {tv, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (etSearchJoke.content.isEmpty()) {
                    showToast(R.string.search_error_type_query)
                } else {
                    finishWithQueryResult(etSearchJoke.content)
                }
                true
            } else {
                false
            }
        }
    }

    private fun finishAnimatingToolbar() {

        val transition = TransitionsFactory.fadeOutWithActionOnEnd(action = ::navigateUp)
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

    private fun setupObservers() {

        viewModel.showProgressEvent.observeNullable(this) {
            progressBar.isVisible = true
        }

        viewModel.hideProgressEvent.observeNullable(this) {
            progressBar.isVisible = false
        }

        viewModel.categories.observeNotNull(this) { categories ->

            tvSuggestions.isVisible = true
            chipGroup.isVisible = true

            for (category in categories) {
                chipGroup.addChip(this, category) {
                    finishWithQueryResult(category)
                }
            }
        }
    }

    private fun loadCategories() {
        //when observing a liveData, it automatically receives the current values
        if (viewModel.categories.value.isNullOrEmpty()) {
            launch {
                viewModel.retrieveJokesCategories()
            }
        }
    }

    private fun finishWithQueryResult(query: String) {
        val intent = Intent().apply {
            putExtra(JokesActivity.EXTRA_JOKE_QUERY, query)
        }
        setResult(Activity.RESULT_OK, intent)
        finishAnimatingToolbar()
    }

    //endregion
}
