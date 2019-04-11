package com.igorvd.chuckfacts.testutils

import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.features.jokes.model.JokeView
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val DUMMY_JOKES = run {

    val list = mutableListOf<Joke>().apply {
        add(
            Joke(
                id = "1",
                categories = listOf(DUMMY_CATEGORIES.first()),
                url = "https://www.google.com",
                value = "Chuck norris contou ate o infinito duas vezes"
            )
        )
        add(
            Joke(
                id = "2",
                categories = listOf(DUMMY_CATEGORIES.first()),
                url = "https://www.google.com",
                value = "Chuck e cheese was actually gonna be called: Chuck Norris cheese. it was " +
                        "then changed for being too violent. Every animatronic for Chuck Norris " +
                        "cheese was violent because they do roundhouse kicks"
            )
        )
    }

    list
}

val DUMMY_JOKESVIEW = run {

    val list = mutableListOf<JokeView>().apply {
        add(
            JokeView(
                id = "1",
                categories = listOf(DUMMY_CATEGORIES.first()),
                url = "https://www.google.com",
                value = "Chuck norris contou ate o infinito duas vezes",
                textSizeRes = R.dimen.text_large
            )
        )
        add(
            JokeView(
                id = "2",
                categories = listOf(DUMMY_CATEGORIES.first()),
                url = "https://www.google.com",
                value = "Chuck e cheese was actually gonna be called: Chuck Norris cheese. it was " +
                        "then changed for being too violent. Every animatronic for Chuck Norris " +
                        "cheese was violent because they do roundhouse kicks",
                textSizeRes = R.dimen.text_small
            )
        )
    }

    list
}

@FlowPreview
fun dummyJokesFlow(jokes: List<Joke>): Flow<List<Joke>> = flow {
    emit(jokes)
}