package com.igorvd.chuckfacts.domain.utils.extensions

/**
 *
 * @author Igor Vilela
 * @since 05/01/2018
 */


val Boolean.int
    get() = if (this) 1 else 0

val Boolean.long
    get() = if (this) 1L else 0L

val Boolean.double
    get() = if (this) 1.0 else 0.0


val Int.boolean
    get() = this == 1

