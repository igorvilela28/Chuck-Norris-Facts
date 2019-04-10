package com.igorvd.chuckfacts.features

import com.igorvd.chuckfacts.features.jokes.model.JokeView

sealed class ScreenState
object NetworkError : ScreenState()
object HttpError : ScreenState()
object EmptyResult : ScreenState()

//FIXME: move this when it becomes possible to inherit from sealed class from another file or maybe use generics
sealed class JokeScreenState : ScreenState() {
    data class Result(val result: List<JokeView>) : JokeScreenState()
}