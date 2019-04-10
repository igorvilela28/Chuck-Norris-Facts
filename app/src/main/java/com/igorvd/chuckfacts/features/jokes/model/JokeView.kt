package com.igorvd.chuckfacts.features.jokes.model

data class JokeView(
    val id: String,
    val categories: List<String>,
    val url: String,
    val value: String,
    val textSizeRes: Int
)