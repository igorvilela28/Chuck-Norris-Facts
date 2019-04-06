package com.igorvd.chuckfacts.features.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveJokesInteractor
import com.igorvd.chuckfacts.features.BaseViewModel
import javax.inject.Inject

class JokesViewModel @Inject constructor(
    private val retrieveJokesInteractor: RetrieveJokesInteractor
): BaseViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>>
        get() = _jokes

    suspend fun retrieveJokes(query: String) {
        val params = RetrieveJokesInteractor.Params(query)
        val jokes = retrieveJokesInteractor.execute(params)
        _jokes.value = jokes
    }

}