package com.igorvd.chuckfacts.testutils

import com.igorvd.chuckfacts.domain.jokes.entity.Joke

val DUMMY_JOKES = run {

    val list = mutableListOf<Joke>()

    for (i in 1..5) {
        val joke = Joke(
            id = i.toString(),
            categories = listOf(DUMMY_CATEGORIES.first()),
            url = "https://www.google.com",
            value = "Chuck norris contou ate o infinito duas vezes"
        )
        list.add(joke)
    }
    list
}