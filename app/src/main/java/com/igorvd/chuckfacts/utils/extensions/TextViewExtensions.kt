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
