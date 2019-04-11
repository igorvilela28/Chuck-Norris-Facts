package com.igorvd.chuckfacts.data.jokes.local.database

import com.igorvd.chuckfacts.domain.jokes.entity.Joke

fun Joke.toJokeEntity(query: String): JokeEntity {
    return JokeEntity(
        id,
        categories,
        url,
        value,
        query.toLowerCase()
    )
}

fun JokeEntity.toJoke(): Joke {
    return Joke(
        id,
        categories,
        url,
        value
    )
}