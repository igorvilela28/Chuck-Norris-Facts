package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.jokes.repository.SearchHistoricRepository
import com.igorvd.chuckfacts.domain.utils.DUMMY_CATEGORIES
import com.igorvd.chuckfacts.domain.utils.DUMMY_SEARCH_HISTORIC
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RetrieveSearchHistoricInteractorTest {

    @MockK
    private lateinit var searchHistoricRepository: SearchHistoricRepository
    private lateinit var interactor: RetrieveSearchHistoricInteractor

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        interactor = RetrieveSearchHistoricInteractor(searchHistoricRepository)
    }

    @Test
    fun `should retrieve random categories`() = runBlocking {

        coEvery { searchHistoricRepository.retrieveAll() } returns DUMMY_SEARCH_HISTORIC

        val historic = interactor.execute(null)

        verify(exactly = 1) { searchHistoricRepository.retrieveAll() }

    }
}