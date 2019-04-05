package com.igorvd.chuckfacts.domain.jokes.entity

data class Joke(
        val id: String,
        val category: String,
        val iconUrl: String,
        val url: String,
        val value: String
)