package com.igorvd.chuckfacts.domain.jokes.repository

interface SearchHistoricRepository {

    fun retrieveAll() : List<String>

    fun save(history: List<String>)

}
