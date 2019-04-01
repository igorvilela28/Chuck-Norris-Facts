@file:Suppress("UNCHECKED_CAST")

package com.igorvd.chuckfacts.utils.extensions

import android.widget.Spinner
import com.igorvd.chuckfacts.utils.adapter.SpinnerHintDropdownAdapter

/**
 *
 * @author Igor Vilela
 * @since 11/04/2018
 */

/**
 * Verifica se o item de "hint" está selecionado pelo spinner
 */
fun Spinner.isHintSelected(): Boolean {
    try {
        val adapter = this.adapter as SpinnerHintDropdownAdapter<*>
        return adapter.hasHint() && this.selectedItemPosition == 0
    } catch (e: ClassCastException) {
        return false
    }
}

/**
 * Retorna o item selecionado pelo Spinner. Retorna nulo nos seguintes casos:
 * 1. hint selecionada.
 * 2. Erro de cast
 */
fun <T> Spinner.getSelectedItemOrNull(): T? {
    try {
        if(isHintSelected()) return null
        return selectedItem as T
    } catch (e: Exception) {
        return null
    }
}

/**
 * Retorna o item selecionado pelo spinner. Segue as mesmas regras de [getSelectedItemOrNull], porém,
 * caso o item retornado seja nullo, irá automaticamente mostrar a mensagem de erro definida em
 * [showErrorMessage]
 */
fun <T> Spinner.getSelectedItemOrShowError(message: String): T? {

    val selectedItem = getSelectedItemOrNull<T>()
    if (selectedItem == null) {
        showErrorMessage(message)
    }
    return selectedItem
}

fun Spinner.showErrorMessage(message: String) {
    context.showToast(message)
}
