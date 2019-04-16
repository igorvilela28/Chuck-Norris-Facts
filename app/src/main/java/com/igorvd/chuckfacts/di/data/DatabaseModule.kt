package com.igorvd.chuckfacts.di.data

import androidx.room.Room
import com.igorvd.chuckfacts.data.jokes.local.database.JokeDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
                get(),
                JokeDatabase::class.java, "database-jokes"
        ).build()
    }
}