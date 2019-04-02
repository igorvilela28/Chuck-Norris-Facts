package com.igorvd.chuckfacts.data

import com.igorvd.chuckfacts.data.jokes.response.GetJokesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Igor Vilela
 * @since 02/04/2019
 */


interface ChuckNorrisApi {

    @GET("jokes/categories")
    fun getCategories(): Response<List<String>>

    @GET("jokes/search")
    fun searchJokes(@Query("query") query: String): Response<GetJokesResponse>

}
