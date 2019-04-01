package com.igorvd.chuckfacts.utils.extensions

import android.view.ViewGroup

/**
 *
 * @author Igor Vilela
 * @since 21/03/2018
 */

/**
 * Retorna a View presente no indice informado.
 */
operator fun ViewGroup.get(index : Int) = getChildAt(index) ?: throw IndexOutOfBoundsException("The index is: $index, but the size is $childCount")

/**
 * Retorna a quantidade de views dentro do ViewGroup.
 */
inline val ViewGroup.size get() = childCount

/**
 * Checa se o ViewGroup está vazio.
 */
inline fun ViewGroup.isEmpty() = childCount == 0

/**
 * Checa se o ViewGroup não está vazio.
 */
inline fun ViewGroup.isNotEmpty() = childCount != 0