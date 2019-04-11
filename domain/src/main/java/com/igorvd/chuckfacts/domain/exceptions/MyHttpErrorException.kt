package com.igorvd.chuckfacts.domain.exceptions


sealed class MyHttpErrorException : MyException {

    constructor(message: String, code: Int): super(message)
    constructor(message: String, code: Int, cause: Throwable): super(message, cause)
}

/**
 * Should be thrown when occurs a 4xx Http error
 */
class HttpClientErrorException : MyHttpErrorException {
    constructor(message: String, code: Int): super(message, code)
    constructor(message: String, code: Int, cause: Throwable): super(message, code, cause)
}


/**
 * Should be thrown when occurs a 5xx Http error
 */
class HttpServerErrorException : MyHttpErrorException {
    constructor(message: String, code: Int): super(message, code)
    constructor(message: String, code: Int, cause: Throwable): super(message, code, cause)
}
