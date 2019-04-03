package com.igorvd.chuckfacts.data.jokes.local

interface JokeCategoryLocalDataSource {

    fun retrieveAll(): List<String>
    fun save(categories: List<String>)

}