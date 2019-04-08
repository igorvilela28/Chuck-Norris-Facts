package com.igorvd.chuckfacts.domain.jokes.interactor

import com.igorvd.chuckfacts.domain.jokes.repository.JokeCategoryRepository
import com.igorvd.chuckfacts.domain.utils.DUMMY_CATEGORIES
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RetrieveCategoriesInteractorTest {

    @MockK
    private lateinit var categoriesRepository: JokeCategoryRepository
    private lateinit var retrieveCategoriesInteractor: RetrieveCategoriesInteractor

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        retrieveCategoriesInteractor = RetrieveCategoriesInteractor(categoriesRepository)
    }

    @Test
    fun `should retrieve random categories`() = runBlocking {

        coEvery { categoriesRepository.retrieveAll() } returns DUMMY_CATEGORIES

        val params = RetrieveCategoriesInteractor.Params(8)
        val categories = retrieveCategoriesInteractor.execute(params)

        assertEquals(8, categories.size)
        for (category in categories) {
            assertTrue(DUMMY_CATEGORIES.contains(category))
        }
    }
}