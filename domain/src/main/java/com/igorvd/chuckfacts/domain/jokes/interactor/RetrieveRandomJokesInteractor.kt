package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.Interactor
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import kotlinx.coroutines.FlowPreview

/**
 * Used to retrieve a list of random [Joke]
 */
class RetrieveRandomJokesInteractor @FlowPreview
constructor(
    private val jokesRepository: JokeRepository
) : Interactor<List<Joke>, RetrieveRandomJokesInteractor.Params> {

    @FlowPreview
    override suspend fun execute(params: Params): List<Joke> {

        val jokes = jokesRepository.retrieveRandomJokes(params.limit)
        return jokes
    }

    data class Params(val limit: Int)

}