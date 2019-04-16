package com.igorvd.chuckfacts.features.jokes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.transition.TransitionManager
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.features.JokeScreenState
import com.igorvd.chuckfacts.features.search.SearchJokeActivity
import com.igorvd.chuckfacts.utils.extensions.*
import com.igorvd.chuckfacts.utils.lifecycle.job
import com.igorvd.chuckfacts.utils.transition.TransitionsFactory
import kotlinx.android.synthetic.main.activity_jokes.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

@FlowPreview
class JokesActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = lifecycle.job + Dispatchers.Main

//    @Inject
//    protected lateinit var viewModelFactory: ViewModelFactory
//    private val viewModel by lazy {
//        ViewModelProviders.of(this, viewModelFactory).get(JokesViewModel::class.java)
//    }

    private val viewModel: JokesViewModel by viewModel()

    private val adapter by lazy {
        JokesAdapter(this, ::shareJokeUrl)
    }

    private val stateMachine by lazy {
        JokesActivityStateMachine(clJokesRoot, adapter)
    }

    private val paramsHolder: Pair<LayoutParams, LayoutParams> by lazy {
        val originalParams = cvSearchBar.layoutParams as LayoutParams
        val newParams = LayoutParams(originalParams)
        newParams.setMargins(0, 0, 0, 0)
        originalParams to newParams
    }

    companion object {
        const val EXTRA_JOKE_QUERY = "query"
        private const val RC_JOKE_QUERY = 9999
    }

    //**************************************************************************
    // region: LIFE CYCLE
    //**************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        setupViews()
        setupObservers()
        retrieveRandomJokes()
    }

    override fun onResume() {
        super.onResume()
        setOriginalBarProperties()
    }

    //endregion

    //**************************************************************************
    // region: ACTIVITY METHODS
    //**************************************************************************

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == RC_JOKE_QUERY && resultCode == Activity.RESULT_OK) {
            val query = data?.getStringExtra(EXTRA_JOKE_QUERY)
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

        btTryAgain.setOnClickListener { retrieveJokes(viewModel.lastQuery) }

        rvJokes.setup(context = this, adapter = adapter)
    }

    private fun setupObservers() = with(viewModel) {

        showProgressEvent.observeNullable(this@JokesActivity) {
            stateMachine.showProgress()
        }

        hideProgressEvent.observeNullable(this@JokesActivity) {
            stateMachine.hideProgress()
        }

        screenState.observeNotNull(this@JokesActivity) { state ->
            stateMachine.setState(state)
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

    private fun shareJokeUrl(url: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            setType("text/plain")
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.jokes_share_url))
            putExtra(Intent.EXTRA_TEXT, url)
        }

        startActivity(Intent.createChooser(intent, getString(R.string.jokes_share_url)))
    }

    private fun retrieveJokes(query: String?) {
        query?.let {
            launch {
                viewModel.retrieveJokes(query)
            }
        }
    }

    private fun retrieveRandomJokes() {
        val currentScreenState = viewModel.screenState.value
        if (currentScreenState is JokeScreenState.Result && currentScreenState.result.isNotEmpty()) {
            return
        }
        launch { viewModel.retrieveRandomJokes() }
    }

    //endregion
}