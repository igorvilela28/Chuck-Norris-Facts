package com.igorvd.chuckfacts.utils

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File

object PreferencesUtils {

    fun clearAllPreferences() {

        val targetContext = InstrumentationRegistry
            .getInstrumentation()
            .getTargetContext()

        val root = targetContext
            .getFilesDir()
            .getParentFile()

        val sharedPreferencesFileNames = File(root, "shared_prefs").list()
        for (fileName in sharedPreferencesFileNames) {
            targetContext
                .getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit()
        }
    }
}