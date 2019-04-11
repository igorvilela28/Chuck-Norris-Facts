package com.igorvd.chuckfacts.data.jokes.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJokes(jokes: List<JokeEntity>): List<Long>

    @Query("SELECT * FROM ${JokeEntity.TABLE_NAME} WHERE ${JokeEntity.FIELD_QUERY} = :query")
    fun retrieveJokes(query: String): List<JokeEntity>

    @Query("SELECT * FROM ${JokeEntity.TABLE_NAME} " +
            "WHERE ${JokeEntity.FIELD_ID} IN " +
            "(SELECT ${JokeEntity.FIELD_ID} FROM ${JokeEntity.TABLE_NAME} ORDER BY RANDOM() LIMIT :limit)")
    fun retrieveRandomJokes(limit: Int): List<JokeEntity>

}