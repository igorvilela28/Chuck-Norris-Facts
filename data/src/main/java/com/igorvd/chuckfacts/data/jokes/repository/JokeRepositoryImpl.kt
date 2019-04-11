package com.igorvd.chuckfacts.data.jokes.repository

import com.igorvd.chuckfacts.data.jokes.local.JokeLocalDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSource
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository that fetch jokes.
 */
@FlowPreview
class JokeRepositoryImpl @Inject constructor(
    private val remoteDataSource: JokeRemoteDataSource,
    private val localDataSource: JokeLocalDataSource
) : JokeRepository {

    override suspend fun retrieveJokes(query: String): Flow<List<Joke>> = flow {

        val localJokes = localDataSource.retrieveJokes(query)

        if (localJokes.isNotEmpty()) {
            emit(localJokes)
        }

        val remoteJokes = remoteDataSource.getJokes(query) - localJokes
        localDataSource.insertJokes(remoteJokes, query)
        emit(remoteJokes)

    }

    override suspend fun retrieveRandomJokes(limit: Int): List<Joke> {
        return localDataSource.retrieveRandomJokes(limit)
    }

}