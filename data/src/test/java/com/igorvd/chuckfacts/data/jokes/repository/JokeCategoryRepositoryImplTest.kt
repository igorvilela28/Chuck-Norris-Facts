package com.igorvd.chuckfacts.data.jokes.repository

import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSource
import com.igorvd.chuckfacts.data.testutils.DUMMY_CATEGORIES
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JokeCategoryRepositoryImplTest {

    @MockK
    private lateinit var remoteDataSource: JokeCategoryRemoteDataSource
    @MockK
    private lateinit var localDataSource: JokeCategoryLocalDataSource
    private lateinit var jokeCategoryRepositoryImpl: JokeCategoryRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        jokeCategoryRepositoryImpl = JokeCategoryRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `should load categories from local data source first`() = runBlocking {

        every { localDataSource.retrieveAll() } returns DUMMY_CATEGORIES

        val categories = jokeCategoryRepositoryImpl.retrieveAll()

        verify (exactly = 1) { localDataSource.retrieveAll() }
        verify { remoteDataSource wasNot Called }
    }

    @Test
    fun `should load categories from remote when local source is empty`() = runBlocking {

        every { localDataSource.retrieveAll() } returns emptyList()
        coEvery { remoteDataSource.retrieveAll() } returns DUMMY_CATEGORIES
        every { localDataSource.save(DUMMY_CATEGORIES) } just Runs

        val categories = jokeCategoryRepositoryImpl.retrieveAll()

        coVerifyOrder {
            localDataSource.retrieveAll()
            remoteDataSource.retrieveAll()
            localDataSource.save(DUMMY_CATEGORIES)
        }
    }
}