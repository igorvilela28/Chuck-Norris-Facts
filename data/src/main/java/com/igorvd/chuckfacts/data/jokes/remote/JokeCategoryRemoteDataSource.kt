package com.igorvd.chuckfacts.data.jokes.remote

interface JokeCategoryRemoteDataSource {

    suspend fun retrieveAll(): List<String>

}