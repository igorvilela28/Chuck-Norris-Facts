package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.jokes.repository.SearchHistoricRepository
import com.igorvd.chuckfacts.domain.testutils.DUMMY_SEARCH_HISTORIC
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddQueryToSearchHistoricInteractorTest {

    @MockK
    private lateinit var searchHistoricRepository: SearchHistoricRepository
    private lateinit var interactor: AddQueryToSearchHistoricInteractor

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        interactor = AddQueryToSearchHistoricInteractor(searchHistoricRepository)
    }

    @Test
    fun `should save query when current historic is empty`() = runBlocking {

        every { searchHistoricRepository.retrieveAll() } returns emptyList()
        every { searchHistoricRepository.save(listOf("dev")) } just Runs


        val params = AddQueryToSearchHistoricInteractor.Params("dev")
        val created = interactor.execute(params)

        verifySequence {
            searchHistoricRepository.retrieveAll()
            searchHistoricRepository.save(listOf("dev"))
        }

    }

    @Test
    fun `should save new queries on first list position`() = runBlocking {

        every { searchHistoricRepository.retrieveAll() } returns DUMMY_SEARCH_HISTORIC.take(3)
        val expectedHistoric = DUMMY_SEARCH_HISTORIC.take(3).toMutableList().apply {
            add(0, "infinite")
        }
        every { searchHistoricRepository.save(expectedHistoric) } just Runs


        val params = AddQueryToSearchHistoricInteractor.Params("infinite")
        val created = interactor.execute(params)

        verifySequence {
            searchHistoricRepository.retrieveAll()
            searchHistoricRepository.save(expectedHistoric)
        }

    }

    @Test
    fun `should save and shift query to first position when current historic already has the query`() = runBlocking {

        every { searchHistoricRepository.retrieveAll() } returns DUMMY_SEARCH_HISTORIC.take(3)
        val expectedHistoric = DUMMY_SEARCH_HISTORIC.take(3).toMutableList().apply {
            remove("dev")
            add(0, "Dev")
        }
        every { searchHistoricRepository.save(expectedHistoric) } just Runs


        val params = AddQueryToSearchHistoricInteractor.Params("Dev")
        val created = interactor.execute(params)

        verifySequence {
            searchHistoricRepository.retrieveAll()
            searchHistoricRepository.save(expectedHistoric)
        }

    }

    @Test
    fun `should drop outdated query when max query historic limit is reached`() = runBlocking {

        every { searchHistoricRepository.retrieveAll() } returns DUMMY_SEARCH_HISTORIC.take(9)
        val expectedHistoric = DUMMY_SEARCH_HISTORIC.take(9).toMutableList().apply {
            add(0, "infinite")
        }
        every { searchHistoricRepository.save(expectedHistoric) } just Runs


        val params = AddQueryToSearchHistoricInteractor.Params("infinite")
        val created = interactor.execute(params)

        verifySequence {
            searchHistoricRepository.retrieveAll()
            searchHistoricRepository.save(expectedHistoric)
        }

    }


}