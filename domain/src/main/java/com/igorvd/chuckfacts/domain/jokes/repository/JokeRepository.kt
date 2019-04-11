package com.igorvd.chuckfacts.domain.jokes.repository

import com.igorvd.chuckfacts.domain.jokes.entity.Joke

interface JokeRepository {

    suspend fun retrieveJokes(query: String): List<Joke>

    suspend fun retrieveRandomJokes(limit: Int): List<Joke>

}