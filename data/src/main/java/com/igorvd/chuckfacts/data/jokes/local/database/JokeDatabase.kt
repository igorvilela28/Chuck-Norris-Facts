package com.igorvd.chuckfacts.data.jokes.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    exportSchema = true,
    entities = [JokeEntity::class]
)
@TypeConverters(ListStringConverter::class)
abstract class JokeDatabase : RoomDatabase() {

    abstract fun jokeDao(): JokeDao

}