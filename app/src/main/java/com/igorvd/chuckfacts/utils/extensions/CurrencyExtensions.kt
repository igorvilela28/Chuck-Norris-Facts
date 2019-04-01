package com.igorvd.chuckfacts.utils.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

/**
 *
 * @author Igor Vilela
 * @since 14/03/2018
 */


/**
 * Formata a moeda para exibição de acordo com o [Locale] do usuário
 * [see](https://stackoverflow.com/a/19072112)
 */
fun formatCurrency(currencyIsoCode: String, number: BigDecimal,
                   locale : Locale = Locale.getDefault()): String {

    val currency = Currency.getInstance(currencyIsoCode)
    val format = NumberFormat.getCurrencyInstance(locale)
    format.currency = currency
    format.maximumFractionDigits = currency.defaultFractionDigits
    return format.format(number)
}