package com.igorvd.chuckfacts.testutils

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.igorvd.chuckfacts.data.jokes.local.database.JokeDatabase

val TEST_JOKES_DB by lazy {
    val context = ApplicationProvider.getApplicationContext<Context>()
    Room.inMemoryDatabaseBuilder(
        context, JokeDatabase::class.java).build()
    }
