package com.igorvd.chuckfacts.features.jokes

import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveJokesInteractor
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveRandomJokesInteractor
import com.igorvd.chuckfacts.features.BaseViewModel
import com.igorvd.chuckfacts.features.EmptyResult
import com.igorvd.chuckfacts.features.JokeScreenState
import javax.inject.Inject

class JokesViewModel @Inject constructor(
    private val retrieveJokesInteractor: RetrieveJokesInteractor,
    private val retrieveRandomJokesInteractor: RetrieveRandomJokesInteractor
) : BaseViewModel() {

    var lastQuery: String? = null

    suspend fun retrieveJokes(query: String) = doWorkWithProgress {

        lastQuery = query

        val params = RetrieveJokesInteractor.Params(query)
        val jokes = retrieveJokesInteractor.execute(params)

        val state = if (jokes.isEmpty()) {
            EmptyResult
        } else {
            JokeScreenState.Result(jokes.toJokesView())
        }

        _screenState.value = state
    }

    suspend fun retrieveRandomJokes() {
        val params = RetrieveRandomJokesInteractor.Params(10)
        val randomJokes = retrieveRandomJokesInteractor.execute(params)
        _screenState.value = JokeScreenState.Result(randomJokes.toJokesView())
    }

    private fun List<Joke>.toJokesView() = this.map { JokesMapper.jokeToJokeView(it) }
}