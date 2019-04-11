package com.igorvd.chuckfacts.data.jokes.local

import com.igorvd.chuckfacts.data.jokes.local.database.JokeDatabase
import com.igorvd.chuckfacts.data.jokes.local.database.toJoke
import com.igorvd.chuckfacts.data.jokes.local.database.toJokeEntity
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JokeLocalDataSourceImpl @Inject constructor(jokeDb: JokeDatabase) : JokeLocalDataSource {

    private val dao by lazy { jokeDb.jokeDao() }

    override suspend fun insertJokes(jokes: List<Joke>, query: String): Boolean = withContext(IO) {
        val ids = dao.insertJokes(jokes.map { it.toJokeEntity(query) })
        ids.size == jokes.size
    }

    override suspend fun retrieveJokes(query: String): List<Joke> = withContext(IO) {
        dao.retrieveJokes(query.toLowerCase()).map { it.toJoke() }
    }

    override suspend fun retrieveRandomJokes(limit: Int): List<Joke> = withContext(IO) {
        dao.retrieveRandomJokes(limit).map { it.toJoke() }
    }


}