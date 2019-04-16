package com.igorvd.chuckfacts.data.jokes.repository

import com.igorvd.chuckfacts.data.jokes.local.JokeLocalDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSource
import com.igorvd.chuckfacts.domain.exceptions.MyHttpErrorException
import com.igorvd.chuckfacts.domain.exceptions.MyIOException
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository that fetch jokes.
 */
@FlowPreview
class JokeRepositoryImpl constructor(
    private val remoteDataSource: JokeRemoteDataSource,
    private val localDataSource: JokeLocalDataSource
) : JokeRepository {

    override suspend fun retrieveJokes(query: String): Flow<List<Joke>> = flow {

        val localJokes = localDataSource.retrieveJokes(query)

        if (localJokes.isNotEmpty()) {
            emit(localJokes)
        }

        try {
            val remoteJokes = remoteDataSource.getJokes(query) - localJokes
            localDataSource.insertJokes(remoteJokes, query)
            emit(remoteJokes)
        } catch (e: Exception) {
            when (e) {
                is MyHttpErrorException, is MyIOException -> {
                    if (localJokes.isEmpty()) throw e
                }
                else -> throw e
            }
        }

    }

    override suspend fun retrieveRandomJokes(limit: Int): List<Joke> {
        return localDataSource.retrieveRandomJokes(limit)
    }

}