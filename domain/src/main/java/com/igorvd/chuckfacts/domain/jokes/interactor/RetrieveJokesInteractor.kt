package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.Interactor
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

/**
 * Used to retrieve a list of [Joke]
 */
@FlowPreview
class RetrieveJokesInteractor constructor(
    private val jokesRepository: JokeRepository
) : Interactor<Flow<List<Joke>>, RetrieveJokesInteractor.Params> {

    override suspend fun execute(params: Params): Flow<List<Joke>> {

        val jokesFlow = jokesRepository.retrieveJokes(params.query)
        return jokesFlow
    }

    data class Params(val query: String)

}