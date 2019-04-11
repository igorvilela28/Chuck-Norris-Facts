package com.igorvd.chuckfacts.domain.utils.extensions


fun List<String>.toStringWithSeparator(separator: String = ","): String {

    var valuesString = fold("", { total, value ->
        total + value + separator
    })


    if (valuesString.isNotEmpty()) {
        valuesString = valuesString.dropLast(separator.length)
    }

    return valuesString

}