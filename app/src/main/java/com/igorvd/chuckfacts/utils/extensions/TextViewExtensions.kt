package com.igorvd.chuckfacts.utils.extensions

import android.widget.TextView

/**
 *
 * @author Igor Vilela
 * @since 21/03/2018
 */

/**
 * Retorna e defifne o conteúdo do TextView.
 */
var TextView.content
    get() = text.toString()
    set(value) {
        text = value
    }

/**
 * Limpa o campo de texto.
 */
fun TextView.clear() {
    text = ""
}

/**
 * Checa se o TextView está em branco
 */
fun TextView.isBlank() = text.isBlank()

/**
 * Checa se o TextView não está em branco
 */
fun TextView.isNotBlank() = text.isNotBlank()

/**
 * Checa se o TextView está vazio.
 */
val TextView.isEmpty get() = length() == 0

/**
 * Checa se o TextView não está vazio.
 */
val TextView.isNotEmpty get() = length() != 0
