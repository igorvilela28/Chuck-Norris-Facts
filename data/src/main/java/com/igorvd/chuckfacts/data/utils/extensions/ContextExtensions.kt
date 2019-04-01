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


fun Context.readAssets(fileName: String): String? {

    var reader: BufferedReader? = null
    var content = ""
    try {
        reader = BufferedReader(InputStreamReader(assets.open(fileName), "UTF-8"))

        // do reading, usually loop until end of file reading
        var line: String? = reader.readLine()
        while (line != null) {
            content += line
            line = reader.readLine()
        }
        return content
    } catch (e: IOException) {
        Timber.e(e, "IO Exception while reading assets")
    } finally {
        IOUtils.close(reader)
    }
    return null
}

val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)