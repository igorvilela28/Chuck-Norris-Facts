package com.igorvd.chuckfacts.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


/**
 *
 * @author Igor Vilela
 * @since 13/04/2018
 */

/**
 * Retorna um [Observer] de somente valores não nulos
 */
fun <T> createObserverNotNull(work: (T) -> Unit): Observer<T> {
    val observer: Observer<T> = Observer { value -> value?.let { work(it) } }
    return observer
}

/**
 * Observa o [LiveData] para mudanças de somente valores não nulos
 */
fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, work: (T) -> Unit) : Observer<T> {

    val observer: Observer<T> = createObserverNotNull(work)
    observe(owner,  observer)
    return observer

}

/**
 * Observa o [LiveData] para mudanças de valores nulaveis
 */
fun <T> LiveData<T>.observeNullable(owner: LifecycleOwner, work: (T?) -> Unit): Observer<T> {

    val observer: Observer<T> = Observer { value -> work(value) }
    observe(owner,  observer)
    return observer

}