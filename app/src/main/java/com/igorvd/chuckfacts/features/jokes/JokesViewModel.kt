package com.igorvd.chuckfacts.features.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveJokesInteractor
import com.igorvd.chuckfacts.features.BaseViewModel
import com.igorvd.chuckfacts.features.jokes.model.JokeView
import com.igorvd.chuckfacts.utils.SingleLiveEvent
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class JokesViewModel @Inject constructor(
    private val retrieveJokesInteractor: RetrieveJokesInteractor
): BaseViewModel() {

    private val _jokes = MutableLiveData<List<JokeView>>()
    val jokes: LiveData<List<JokeView>>
        get() = _jokes

    private val _showEmptyJokesResult = SingleLiveEvent<Void>()
        val showEmptyJokesResult: LiveData<Void>
            get() = _showEmptyJokesResult

    suspend fun retrieveJokes(query: String) = doWorkWithProgress {
        val params = RetrieveJokesInteractor.Params(query)
        val jokes = retrieveJokesInteractor.execute(params)

        if (jokes.isEmpty()) {
            _showEmptyJokesResult.call()
        } else {
            _jokes.value = jokes.map { JokesMapper.jokeToJokeView(it) }
        }
    }

}