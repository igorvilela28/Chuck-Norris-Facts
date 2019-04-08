package com.igorvd.chuckfacts.data.jokes.repository

import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSource
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.repository.JokeCategoryRepository
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import javax.inject.Inject

/**
 * Repository that fetch jokes.
 */
class JokeRepositoryImpl @Inject constructor(
    private val remoteDataSource: JokeRemoteDataSource
) : JokeRepository {

    override suspend fun retrieveJokes(query: String): List<Joke> {

        return remoteDataSource.getJokes(query)

    }

}