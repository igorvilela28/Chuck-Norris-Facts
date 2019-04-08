package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.Interactor
import com.igorvd.chuckfacts.domain.jokes.repository.SearchHistoricRepository
import javax.inject.Inject


/**
 * Used to retrieve a list that contains the user search historic
 */
class RetrieveSearchHistoricInteractor @Inject constructor(
    private val searchHistoricRepository: SearchHistoricRepository
) : Interactor<List<String>, Void?> {

    override suspend fun execute(params: Void?): List<String> {
        val historic = searchHistoricRepository.retrieveAll()
        return historic
    }
}