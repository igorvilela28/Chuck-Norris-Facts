package com.igorvd.chuckfacts.data.utils.extensions

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
/**
 * @author Igor VIlela
 * @since 02/01/2018
 */

val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

fun Context.sharedPreferences(name: String): SharedPreferences {
    return getSharedPreferences(name, Context.MODE_PRIVATE)
}