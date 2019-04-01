package com.igorvd.chuckfacts.domain.utils.extensions

/**
 *
 * @author Igor Vilela
 * @since 29/01/2018
 */


fun<T> List<T>?.isNullOrEmpty(): Boolean = (this == null) || this.isEmpty()

fun<T> List<T>?.isNotNullOrEmpty(): Boolean = this?.isNotEmpty() ?: false

fun<T> MutableList<T>.removeIff(predicate: (T) -> Boolean) {

    val filtered = this.filter(predicate)

    filtered.forEach { this.remove(it) }

}