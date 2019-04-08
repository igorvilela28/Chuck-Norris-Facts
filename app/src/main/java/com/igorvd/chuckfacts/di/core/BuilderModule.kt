package com.igorvd.chuckfacts.di.core

import android.app.Activity
import android.app.Service
import com.igorvd.chuckfacts.di.domain.CategoryModule
import com.igorvd.chuckfacts.di.domain.JokeModule
import com.igorvd.chuckfacts.di.features.JokesModule
import com.igorvd.chuckfacts.di.features.SearchJokeModule
import com.igorvd.chuckfacts.features.jokes.JokesActivity
import com.igorvd.chuckfacts.features.search.SearchJokeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module that contains the [ContributesAndroidInjector] implementations for injecting the concrete
 * Android framework classes: [Activity] - [Fragment] - [Service]
 * @author Igor Vilela
 * @since 27/12/17
 */

@Module
abstract class BuilderModule {


    //**************************************************************************
    // region: Modules list
    //**************************************************************************

    @ContributesAndroidInjector(
        modules = arrayOf(
            JokeModule::class,
            JokesModule::class
        )
    )
    abstract fun contributesJokesActivity(): JokesActivity

    @ContributesAndroidInjector(
        modules = arrayOf(
            CategoryModule::class,
            SearchJokeModule::class
        )
    )
    abstract fun contributesSearchJokeActivity(): SearchJokeActivity

    //endregion
}