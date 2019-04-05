package com.igorvd.chuckfacts.data.utils.extensions

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.igorvd.chuckfacts.data.utils.IOUtils
import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * @author Igor VIlela
 * @since 02/01/2018
 */

val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

fun Context.sharedPreferences(name: String): SharedPreferences {
    return getSharedPreferences(name, Context.MODE_PRIVATE)
}