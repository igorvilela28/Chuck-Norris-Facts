package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.Interactor
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveCategoriesInteractor.Params
import com.igorvd.chuckfacts.domain.jokes.repository.JokeCategoryRepository
import com.igorvd.chuckfacts.domain.utils.extensions.getRandomElements
import javax.inject.Inject


private const val DEFAULT_AMOUNT = 8

/**
 * Used to retrieve a list of [Joke] categories. It will randomize an amount of categories to be
 * retrieved, using the amount passed on [Params.amount]
 */
class RetrieveCategoriesInteractor @Inject constructor(
    private val jokeCategoryRepository: JokeCategoryRepository
) : Interactor<List<String>, Params> {

    override suspend fun execute(params: Params): List<String> {

        val categories = jokeCategoryRepository.retrieveAll()
        return categories.getRandomElements(params.amount)

    }

    data class Params(val amount: Int = DEFAULT_AMOUNT)

}