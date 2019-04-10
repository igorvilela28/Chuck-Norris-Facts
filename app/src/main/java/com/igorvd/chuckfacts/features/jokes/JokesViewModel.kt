package com.igorvd.chuckfacts.features.jokes

import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveJokesInteractor
import com.igorvd.chuckfacts.features.BaseViewModel
import com.igorvd.chuckfacts.features.EmptyResult
import com.igorvd.chuckfacts.features.JokeScreenState
import javax.inject.Inject

class JokesViewModel @Inject constructor(
    private val retrieveJokesInteractor: RetrieveJokesInteractor
) : BaseViewModel() {

    var lastQuery: String? = null

    suspend fun retrieveJokes(query: String) = doWorkWithProgress {

        lastQuery = query

        val params = RetrieveJokesInteractor.Params(query)
        val jokes = retrieveJokesInteractor.execute(params)

        val state = if (jokes.isEmpty()) {
            EmptyResult
        } else {
            JokeScreenState.Result(jokes.map { JokesMapper.jokeToJokeView(it) })
        }

        _screenState.value = state
    }
}