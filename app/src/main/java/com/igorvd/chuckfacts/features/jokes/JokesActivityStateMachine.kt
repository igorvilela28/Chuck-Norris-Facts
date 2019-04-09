package com.igorvd.chuckfacts.features.jokes

import android.view.View
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.features.*
import com.igorvd.chuckfacts.features.jokes.model.JokeView
import com.igorvd.chuckfacts.utils.extensions.isVisible
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_jokes.*
import kotlinx.android.synthetic.main.error_layout.*

/**
 * Use to change the jokes screen state
 */
class JokesActivityStateMachine(
    override val containerView: View,
    private val adapter: JokesAdapter) : LayoutContainer {

    fun setState(state: ScreenState) {
        when (state) {
            is JokeScreenState.Result -> setHasResults(state.result)
            is EmptyResult -> setEmptyResults()
            is HttpError -> setHttpError()
            is NetworkError -> setNetworkError()
        }
    }

    fun showProgress() {
        errorLayout.isVisible = false
        progressBarContainer.isVisible = true
        adapter.submitList(emptyList())
    }

    fun hideProgress() {
        progressBarContainer.isVisible = false
    }

    private fun setHasResults(result: List<JokeView>) {
        adapter.submitList(result)
    }

    private fun setEmptyResults() {
        errorLayout.isVisible = true
        btTryAgain.isVisible = false
        ivErrorIcon.setImageResource(R.drawable.empty_results)
        tvErrorMessage.setText(R.string.jokes_no_results)
    }

    private fun setHttpError() {
        errorLayout.isVisible = true
        btTryAgain.isVisible = true
        ivErrorIcon.setImageResource(R.drawable.error_server)
        tvErrorMessage.setText(R.string.jokes_error_server)
    }

    private fun setNetworkError() {
        errorLayout.isVisible = true
        btTryAgain.isVisible = true
        ivErrorIcon.setImageResource(R.drawable.error_networking)
        tvErrorMessage.setText(R.string.jokes_error_network)
    }

}

