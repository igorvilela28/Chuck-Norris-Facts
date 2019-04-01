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

fun Context.getColorCompat(@ColorRes colorId: Int): Int = ContextCompat.getColor(this, colorId)

fun Context.checkPermission(permission: String): Boolean {

    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        return true
    }

    return ContextCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}

/**
 * returns the activity associated with the context, returns null in case there is no activity associated
 * to it
 */
fun Context.getActivity(): Activity? {
    return when {
        this is Activity -> this
        this is Fragment -> this.activity
        this is SupportFragment -> this.activity
        else -> null
    }
}

fun Context.vibrator(): Vibrator {

    return this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
}

fun Context.showToast(message: String, length: Int = Toast.LENGTH_LONG) {

    Toast.makeText(this, message, length).show()
}

fun Context.showToast(message: Int, length: Int = Toast.LENGTH_LONG) {

    showToast(getString(message), length)
}

fun Vibrator.mVibrate(milliseconds: Long = 400L) {

    if (Build.VERSION.SDK_INT >= 26) {
        vibrate(VibrationEffect.createOneShot(milliseconds,VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrate(milliseconds)
    }
}

/**
 * Wrapper to automatically catch the possible [IllegalStateException] throw by the
 * [MediaPlayer.start]
 */
fun MediaPlayer.mStart() {

    try {
        start()
    } catch (e: IllegalStateException) {
        Timber.w(e, "error while playing audio")
    }
}
