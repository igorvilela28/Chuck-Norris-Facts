package com.igorvd.chuckfacts.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igorvd.chuckfacts.domain.jokes.interactor.RetrieveCategoriesInteractor
import com.igorvd.chuckfacts.features.BaseViewModel
import javax.inject.Inject

class SearchJokeViewModel @Inject constructor(
    private val retrieveCategoriesInteractor: RetrieveCategoriesInteractor
): BaseViewModel() {

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    suspend fun retrieveJokesCategories() = doWorkWithProgress {

        val params = RetrieveCategoriesInteractor.Params()
        _categories.value = retrieveCategoriesInteractor.execute(params)

    }
}