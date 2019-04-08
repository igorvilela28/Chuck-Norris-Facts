package com.igorvd.chuckfacts.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igorvd.chuckfacts.domain.jokes.interactor.AddQueryToSearchHistoricInteractor
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveCategoriesInteractor
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveSearchHistoricInteractor
import com.igorvd.chuckfacts.features.BaseViewModel
import com.igorvd.chuckfacts.utils.SingleLiveEvent
import javax.inject.Inject

class SearchJokeViewModel @Inject constructor(
    private val retrieveCategoriesInteractor: RetrieveCategoriesInteractor,
    private val retrieveSearchHistoricInteractor: RetrieveSearchHistoricInteractor,
    private val addQueryToSearchHistoricInteractor: AddQueryToSearchHistoricInteractor
): BaseViewModel() {

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    private val _searchHistoric = MutableLiveData<List<String>>()
    val searchHistoric: LiveData<List<String>>
        get() = _searchHistoric

    private val _onQueryAddedToHistoric = SingleLiveEvent<Void>()
    val onQueryAddedToHistoric: LiveData<Void>
        get() = _onQueryAddedToHistoric

    suspend fun retrieveJokesCategories() = doWorkWithProgress {

        val params = RetrieveCategoriesInteractor.Params()
        _categories.value = retrieveCategoriesInteractor.execute(params)

    }

    suspend fun retrieveSearchHistoric() {
        val historic = retrieveSearchHistoricInteractor.execute(null)
        _searchHistoric.value = historic
    }

    suspend fun addQueryToSearchHistoric(query: String) {
        val params = AddQueryToSearchHistoricInteractor.Params(query)
        val isAdded = addQueryToSearchHistoricInteractor.execute(params)
        _onQueryAddedToHistoric.call()
    }
}