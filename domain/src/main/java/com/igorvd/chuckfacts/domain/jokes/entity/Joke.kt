package com.igorvd.chuckfacts.domain.jokes.entity

data class Joke(
        val id: String,
        val categories: List<String>,
        val url: String?,
        val value: String
)