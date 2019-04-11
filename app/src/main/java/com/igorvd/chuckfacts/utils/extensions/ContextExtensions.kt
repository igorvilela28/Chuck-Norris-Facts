package com.igorvd.chuckfacts.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment as SupportFragment

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

