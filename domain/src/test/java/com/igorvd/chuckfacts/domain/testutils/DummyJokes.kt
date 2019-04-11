package com.igorvd.chuckfacts.domain.testutils

import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val DUMMY_JOKES = run {

    val list = mutableListOf<Joke>()

    for (i in 1..5) {
        val joke = Joke(
            id = i.toString(),
            categories = DUMMY_CATEGORIES,
            url = "https://www.google.com",
            value = "Chuck norris contou ate o infinito duas vezes"
        )
        list.add(joke)
    }
    list
}

@FlowPreview
fun dummyJokesFlow(): Flow<List<Joke>> = flow {
    emit(DUMMY_JOKES)
}