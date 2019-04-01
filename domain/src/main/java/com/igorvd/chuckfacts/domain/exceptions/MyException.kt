package com.igorvd.chuckfacts.domain.exceptions

/**
 *
 * @author Igor Vilela
 * @since 11/01/2018
 */
abstract class MyException: Exception {

    override val message: String
        get() = super.message ?: ""

    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)

}