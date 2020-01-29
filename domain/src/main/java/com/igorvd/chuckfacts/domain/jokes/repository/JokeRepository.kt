package com.igorvd.chuckfacts.domain.jokes.repository

import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

interface JokeRepository {

    suspend fun retrieveJokes(query: String): Flow<List<Joke>>

    suspend fun retrieveRandomJokes(limit: Int): List<Joke>

}