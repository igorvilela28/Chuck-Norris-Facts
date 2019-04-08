package com.igorvd.chuckfacts.data.jokes.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.igorvd.chuckfacts.data.testutils.DUMMY_SEARCH_HISTORIC
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchHistoricRepositoryImplTest {

    private lateinit var searchHistoricRepositoryImpl: SearchHistoricRepositoryImpl
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        searchHistoricRepositoryImpl = SearchHistoricRepositoryImpl(context)
    }

    @Test
    fun `should save search historic on local data source`() {
        searchHistoricRepositoryImpl.save(DUMMY_SEARCH_HISTORIC)
        val historic = searchHistoricRepositoryImpl.retrieveAll()
        assertEquals(DUMMY_SEARCH_HISTORIC, historic)
    }

    @Test
    fun `should retrieve empty search historic`() {
        val historic = searchHistoricRepositoryImpl.retrieveAll()
        assertEquals(emptyList<String>(), historic)
    }

}