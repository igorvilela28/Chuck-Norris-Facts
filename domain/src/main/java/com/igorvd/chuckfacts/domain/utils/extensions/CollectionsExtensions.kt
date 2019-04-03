package com.igorvd.chuckfacts.domain.utils.extensions

import java.lang.IllegalArgumentException

/**
 *
 * @author Igor Vilela
 * @since 03/04/2018
 */

/**
 * Return a list that contains random elements from the original list.
 * @param [amount] the amount of random elements to be added on the new list
 */
fun <T> List<T>.getRandomElements(amount: Int): List<T> = this.shuffled().take(amount)
