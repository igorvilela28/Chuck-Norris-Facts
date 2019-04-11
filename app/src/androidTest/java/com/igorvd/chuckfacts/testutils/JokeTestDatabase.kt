package com.igorvd.chuckfacts.testutils

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.igorvd.chuckfacts.data.jokes.local.database.JokeDatabase
import com.igorvd.chuckfacts.data.jokes.local.database.JokeEntity
import com.igorvd.chuckfacts.domain.jokes.entity.Joke

object JokeTestDatabase {

    val DEFAULT_JOKE_ENTITY = JokeEntity(
        id = "1",
        categories = listOf("dev"),
        url = "https://www.google.com",
        value = "Chuck norris contou ate o infinito duas vezes",
        query = "dev"
    )

    val TEST_JOKES_DB by lazy {
        val context = ApplicationProvider.getApplicationContext<Context>()
        Room.inMemoryDatabaseBuilder(
            context, JokeDatabase::class.java
        ).build()
    }

    fun clearDb() = TEST_JOKES_DB.clearAllTables()

    /**
     * Insert copies of [DEFAULT_JOKE_ENTITY]
     */
    fun insertJokes(amount: Int, query: String) {
        val dao = TEST_JOKES_DB.jokeDao()
        val jokes = mutableListOf<JokeEntity>()
        for (i in 0 until amount) {
            jokes.add(DEFAULT_JOKE_ENTITY.copy(id = i.toString(), query = query))
        }
        dao.insertJokes(jokes)
    }
}


