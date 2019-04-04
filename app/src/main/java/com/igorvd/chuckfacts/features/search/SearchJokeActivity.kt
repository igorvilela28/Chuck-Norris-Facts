package com.igorvd.chuckfacts.features.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.core.app.NavUtils
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.utils.ViewModelFactory
import com.igorvd.chuckfacts.utils.extensions.addChip
import com.igorvd.chuckfacts.utils.extensions.hideContent
import com.igorvd.chuckfacts.utils.extensions.observeNotNull
import com.igorvd.chuckfacts.utils.lifecycle.job
import com.igorvd.chuckfacts.utils.transition.TransitionsFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_joke.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
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

        viewModel.categories.observeNotNull(this) { categories ->
            Timber.d(categories.toString())

            for (category in categories) {
                chipGroup.addChip(this, category) {
                    Timber.d("chip clicked: $category")
                }
            }

        }

        launch {
            viewModel.retrieveJokesCategories()
        }
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

    //endregion
}
