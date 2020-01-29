package com.igorvd.chuckfacts.features.jokes

import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveJokesInteractor
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveRandomJokesInteractor
import com.igorvd.chuckfacts.features.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import javax.inject.Inject

class JokesViewModel @Inject constructor(
    private val retrieveJokesInteractor: RetrieveJokesInteractor,
    private val retrieveRandomJokesInteractor: RetrieveRandomJokesInteractor
) : BaseViewModel() {

    var lastQuery: String? = null

    suspend fun retrieveJokes(query: String) {
        lastQuery = query
        val currentJokes = mutableListOf<Joke>()
        collectJokes(query, currentJokes)
    }

    private suspend fun collectJokes(query: String, currentJokes: MutableList<Joke>) {
        try {
            _showProgressEvent.call()

            val params = RetrieveJokesInteractor.Params(query)
            val jokesFlow = retrieveJokesInteractor.execute(params)

            jokesFlow.collect { jokes ->
                currentJokes.addAll(jokes)
                val state = if (currentJokes.isEmpty()) {
                    EmptyResult
                } else {
                    _hideProgressEvent.call()
                    JokeScreenState.Result(currentJokes.toJokesView())
                }
                _screenState.value = state
            }

        } catch (e: Exception) {
            handleProgressException(e)
        } finally {
            if (currentJokes.isEmpty()) _hideProgressEvent.call()
        }
    }

    suspend fun retrieveRandomJokes() {
        val params = RetrieveRandomJokesInteractor.Params(10)
        val randomJokes = retrieveRandomJokesInteractor.execute(params)
        _screenState.value = JokeScreenState.Result(randomJokes.toJokesView())
    }

    private fun List<Joke>.toJokesView() = this.map { JokesMapper.jokeToJokeView(it) }

}