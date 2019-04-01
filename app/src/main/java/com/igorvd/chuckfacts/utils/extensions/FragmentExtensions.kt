package com.igorvd.chuckfacts.utils.extensions

import androidx.fragment.app.Fragment


/**
 *
 * @author Igor Vilela
 * @since 23/03/2018
 */


/**
 * Retorna uma instância a partir do argumento de entrada e extrai o argumento a partir do tipo
 * [T] mapeado para a a entrada [key] sem valor default.
 * Ex:
 * class Fragment : Fragment() {
 *     private val name by argument<String>("userName")
 * }
 */
inline fun <reified T> Fragment.argument(key: String): Lazy<T> = kotlin.lazy {
    val value = arguments?.get(key)
    if (value is T) {
        value
    } else {
        throw IllegalArgumentException("Couldn't find extra with key \"$key\" from type " +
                T::class.java.canonicalName)
    }
}

/**
 * Retorna uma instância a partir da Intent de entrada e extrai o extra a partir do tipo [T]
 * mapeado para a a entrada [key], se não for encontrado o valor será retornado o resultado
 * da função [default]
 * Ex:
 * class Fragment : Fragment() {
 *     private val name by argument<String>("userName", { "prosegur" } )
 * }
 */
inline fun <reified T> Fragment.argument(key: String, crossinline default: () -> T): Lazy<T> = kotlin.lazy {
    val value = arguments?.get(key)
    if (value is T) value else default()
}

fun Fragment.stringRes(resourceRes: Int, vararg args: String): Lazy<String> = kotlin.lazy {

    if(args.isEmpty()) {
        getString(resourceRes)
    } else {
        getString(resourceRes, args)
    }
}