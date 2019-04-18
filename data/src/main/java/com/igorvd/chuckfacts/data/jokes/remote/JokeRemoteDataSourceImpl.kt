package com.igorvd.chuckfacts.data.jokes.remote

import android.content.Context
import com.igorvd.chuckfacts.data.R
import com.igorvd.chuckfacts.data.jokes.remote.api.ChuckNorrisApi
import com.igorvd.chuckfacts.data.jokes.remote.response.JokeResponse
import com.igorvd.chuckfacts.data.network.requests.RequestMaker
import com.igorvd.chuckfacts.domain.jokes.entity.Joke

class JokeRemoteDataSourceImpl constructor(
    private val context: Context,
    private val chuckNorrisApi: ChuckNorrisApi,
    private val requestMaker: RequestMaker
) : JokeRemoteDataSource {

    override suspend fun getJokes(query: String): List<Joke> {
        val call = chuckNorrisApi.searchJokes(query)
        val result = requestMaker.getResult(call)
        return result.jokeResponse.map { it.toJoke() }
    }

    private fun JokeResponse.toJoke(): Joke {

        val newCategories = this.categories ?: listOf(context.getString(R.string.default_category).toUpperCase())

        return Joke(
            this.id,
            newCategories,
            this.url,
            this.value
        )
    }
}