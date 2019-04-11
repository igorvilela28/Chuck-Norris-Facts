package com.igorvd.chuckfacts.data.jokes.remote.api

import com.igorvd.chuckfacts.data.jokes.remote.response.GetJokesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Igor Vilela
 * @since 02/04/2019
 */


interface ChuckNorrisApi {

    @GET("jokes/categories")
    fun getCategories(): Call<List<String>>

    @GET("jokes/search")
    fun searchJokes(@Query("query") query: String): Call<GetJokesResponse>

}
