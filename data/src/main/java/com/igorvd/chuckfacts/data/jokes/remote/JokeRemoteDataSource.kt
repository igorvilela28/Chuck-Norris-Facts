package com.igorvd.chuckfacts.data.jokes.remote

import com.igorvd.chuckfacts.domain.jokes.entity.Joke

interface JokeRemoteDataSource {

    suspend fun getJokes(query: String): List<Joke>

}