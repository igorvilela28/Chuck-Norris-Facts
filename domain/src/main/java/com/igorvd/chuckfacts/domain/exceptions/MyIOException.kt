package com.igorvd.chuckfacts.domain.exceptions

/**
 *
 * @author Igor Vilela
 * @since 11/01/2018
 */
class MyIOException(message: String, cause: Throwable) : MyException(message, cause)