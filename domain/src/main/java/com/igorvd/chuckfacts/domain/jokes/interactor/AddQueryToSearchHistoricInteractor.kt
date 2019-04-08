package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.Interactor
import com.igorvd.chuckfacts.domain.jokes.repository.SearchHistoricRepository
import javax.inject.Inject


/**
 * Add a new item to the user search historic
 */
class AddQueryToSearchHistoricInteractor @Inject constructor(
    private val searchHistoricRepository: SearchHistoricRepository
) : Interactor<Boolean, AddQueryToSearchHistoricInteractor.Params> {

    companion object {
        private const val MAX_HISTORIC_ITEMS = 10
    }

    override suspend fun execute(params: Params): Boolean {

        val currentHistoric = searchHistoricRepository
            .retrieveAll()
            .toMutableList()

        //add first item
        if (currentHistoric.isEmpty()) {
            searchHistoricRepository.save(listOf(params.query))
            return true
        }

        //avoid duplicated entries
        val currentHistoricLower = currentHistoric.map { it.toLowerCase() }
        if (currentHistoricLower.contains(params.query.toLowerCase())) {
            val index = currentHistoricLower.indexOf(params.query.toLowerCase())
            currentHistoric.removeAt(index)
            currentHistoric.add(0, params.query)
            searchHistoricRepository.save(currentHistoric)
            return true
        }

        //add query and avoid historic to be bigger than the max limit
        currentHistoric.add(0, params.query)
        val historicToSave = if (currentHistoric.size > MAX_HISTORIC_ITEMS) {
            currentHistoric.dropLast(1)
        } else currentHistoric

        searchHistoricRepository.save(historicToSave)
        return true
    }

    data class Params(val query: String)
}