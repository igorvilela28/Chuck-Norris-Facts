package com.igorvd.chuckfacts.data.jokes.remote.response

import com.google.gson.annotations.SerializedName


data class GetJokesResponse(
        @SerializedName("jokeResponse")
        val jokeResponse: List<JokeResponse>,
        @SerializedName("total")
        val total: Int
)

data class JokeResponse(
        @SerializedName("id")
        val id: String,
        @SerializedName("category")
        val category: String?,
        @SerializedName("icon_url")
        val iconUrl: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("value")
        val value: String
)