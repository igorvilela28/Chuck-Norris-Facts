package com.igorvd.chuckfacts.domain.jokes.repository

interface JokeCategoryRepository {

    suspend fun retrieveAll(): List<String>

}