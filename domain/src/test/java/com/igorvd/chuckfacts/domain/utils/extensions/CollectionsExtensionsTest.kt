package com.igorvd.chuckfacts.domain.utils.extensions

import org.junit.Test

import org.junit.Assert.*

class CollectionsExtensionsTest {

    
    @Test
    fun `should get random elements from original list`() {

        var amount = 0
        val list = generateSequence { amount++.takeIf { it <=10 } }.toList()

        val random = list.getRandomElements(5).distinct()

        assertEquals(5, random.size)

        for (element in random) {
            assertTrue(list.contains(element))
        }

    }

}