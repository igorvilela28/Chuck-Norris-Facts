package com.igorvd.chuckfacts.data.jokes.remote

import com.igorvd.chuckfacts.data.jokes.remote.api.ChuckNorrisApi
import com.igorvd.chuckfacts.data.network.requests.RequestMaker
import javax.inject.Inject

class JokeCategoryRemoteDataSourceImpl @Inject constructor(
    private val chuckNorrisApi: ChuckNorrisApi,
    private val requestMaker: RequestMaker
) : JokeCategoryRemoteDataSource {

    override suspend fun retrieveAll(): List<String> {
        val call = chuckNorrisApi.getCategories()
        return requestMaker.getResult(call)
    }
}