package com.igorvd.chuckfacts.data.jokes.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.igorvd.chuckfacts.data.testutils.DUMMY_CATEGORIES
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class JokeCategoryLocalDataSourceImplTest {

    private lateinit var jokeCategoryLocalDataSourceImpl: JokeCategoryLocalDataSourceImpl
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        jokeCategoryLocalDataSourceImpl = JokeCategoryLocalDataSourceImpl(context)
    }

    @Test
    fun `should save categories on local data source`() {
        jokeCategoryLocalDataSourceImpl.save(DUMMY_CATEGORIES)
        val categories = jokeCategoryLocalDataSourceImpl.retrieveAll()
        assertEquals(DUMMY_CATEGORIES, categories)
    }

    @Test
    fun `should retrieve empty categories`() {
        val historic = jokeCategoryLocalDataSourceImpl.retrieveAll()
        assertEquals(emptyList<String>(), historic)
    }

}