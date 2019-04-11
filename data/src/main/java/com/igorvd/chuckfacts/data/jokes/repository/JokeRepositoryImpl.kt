package com.igorvd.chuckfacts.data.jokes.repository

import com.igorvd.chuckfacts.data.jokes.local.JokeLocalDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSource
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import javax.inject.Inject

/**
 * Repository that fetch jokes.
 */
class JokeRepositoryImpl @Inject constructor(
    private val remoteDataSource: JokeRemoteDataSource,
    private val jokeLocalDataSource: JokeLocalDataSource
) : JokeRepository {

    override suspend fun retrieveJokes(query: String): List<Joke> {

        val localJokes = jokeLocalDataSource.retrieveJokes(query)

        if (localJokes.isNotEmpty()) {
            return localJokes
        }

        val remoteJokes = remoteDataSource.getJokes(query)
        jokeLocalDataSource.insertJokes(remoteJokes, query)
        return remoteJokes

    }

}