package com.igorvd.chuckfacts.data.utils.extensions

import android.content.SharedPreferences


/**
 * Apply changes to the SharedPreferences automatically calling the apply method
 */
inline fun SharedPreferences.edit(action: (SharedPreferences.Editor) -> SharedPreferences.Editor) {
    val editor = edit()
    action(editor)
    editor.apply()

}