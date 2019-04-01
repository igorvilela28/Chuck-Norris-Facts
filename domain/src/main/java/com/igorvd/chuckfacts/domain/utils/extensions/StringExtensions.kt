package com.igorvd.chuckfacts.domain.utils.extensions

/**
 * @author Igor VIlela
 * @since 03/01/2018
 */


fun List<String>?.toStringWithSeparator(separator: String = ",") : String? {

    if (this == null) return null

    var valuesString = fold("", { total, value ->
        total + value + separator
    })

    valuesString.let {
        if(it.isNotEmpty()) {
            valuesString = it.dropLast(separator.length)
        }
    }

    return valuesString

}

fun String.containsDigit(): Boolean = this.filter { it.isDigit() }.isNotEmpty()

fun String.hasOnlyLetters(): Boolean = this.filter { it.isLetter() }.length == this.length