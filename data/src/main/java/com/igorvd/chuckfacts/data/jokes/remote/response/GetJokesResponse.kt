package com.igorvd.chuckfacts.data.jokes.remote.response

import com.google.gson.annotations.SerializedName


data class GetJokesResponse(
        @SerializedName("result")
        val jokeResponse: List<JokeResponse>,
        @SerializedName("total")
        val total: Int
)

data class JokeResponse(
        @SerializedName("id")
        val id: String,
        @SerializedName("category")
        val categories: List<String>?,
        @SerializedName("url")
        val url: String,
        @SerializedName("value")
        val value: String
)