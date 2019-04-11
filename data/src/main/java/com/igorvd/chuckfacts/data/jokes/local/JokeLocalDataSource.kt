package com.igorvd.chuckfacts.data.jokes.local

import com.igorvd.chuckfacts.domain.jokes.entity.Joke

interface JokeLocalDataSource {

    suspend fun insertJokes(jokes: List<Joke>, query: String): Boolean

    suspend fun retrieveJokes(query: String): List<Joke>

    suspend fun retrieveRandomJokes(limit: Int): List<Joke>

}