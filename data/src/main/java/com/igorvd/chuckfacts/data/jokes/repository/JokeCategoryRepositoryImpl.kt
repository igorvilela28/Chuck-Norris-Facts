package com.igorvd.chuckfacts.data.jokes.repository

import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSource
import com.igorvd.chuckfacts.domain.jokes.repository.JokeCategoryRepository
import javax.inject.Inject

/**
 * Repository that fetch categories. It has a local first policy.
 */
class JokeCategoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: JokeCategoryRemoteDataSource,
    private val localDataSource: JokeCategoryLocalDataSource
) : JokeCategoryRepository {

    override suspend fun retrieveAll(): List<String> {

        val localCategories = localDataSource.retrieveAll()

        if (localCategories.isNotEmpty()) {
            return localCategories
        }

        val remoteCategories = remoteDataSource.retrieveAll()
        localDataSource.save(remoteCategories)
        return remoteCategories
    }
}