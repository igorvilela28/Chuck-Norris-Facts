package com.igorvd.chuckfacts.utils.extensions

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment as SupportFragment
import timber.log.Timber

/**
 * @author Igor Vilela
 * @since 02/01/2018
 */


fun Context.showToast(message: String, length: Int = Toast.LENGTH_LONG) {

    Toast.makeText(this, message, length).show()
}

fun Context.showToast(message: Int, length: Int = Toast.LENGTH_LONG) {

    showToast(getString(message), length)
}

