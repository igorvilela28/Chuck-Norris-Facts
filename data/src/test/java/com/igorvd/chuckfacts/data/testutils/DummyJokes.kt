package com.igorvd.chuckfacts.data.testutils

import com.igorvd.chuckfacts.data.jokes.local.database.toJokeEntity
import com.igorvd.chuckfacts.domain.jokes.entity.Joke

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

fun getDummyJokesEntities(query: String) = DUMMY_JOKES.map { it.toJokeEntity(query) }

