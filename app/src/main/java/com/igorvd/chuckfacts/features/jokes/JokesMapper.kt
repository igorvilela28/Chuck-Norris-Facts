package com.igorvd.chuckfacts.features.jokes

import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.domain.jokes.entity.Joke
import com.igorvd.chuckfacts.features.jokes.model.JokeView

object JokesMapper {

    fun jokeToJokeView(joke: Joke): JokeView {

        val textSizeRes = if (joke.value.length <= 80) R.dimen.text_large else R.dimen.text_small

        return JokeView(
            joke.id,
            joke.categories,
            joke.url,
            joke.value,
            textSizeRes
        )
    }
}