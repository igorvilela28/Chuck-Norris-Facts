package com.igorvd.chuckfacts.domain.jokes

data class Joke(
        val id: String,
        val category: String,
        val iconUrl: String,
        val url: String,
        val value: String
)