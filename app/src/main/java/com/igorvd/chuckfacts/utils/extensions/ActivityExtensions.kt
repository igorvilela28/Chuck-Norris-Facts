package com.igorvd.chuckfacts.utils.extensions

import android.app.Activity
import android.content.DialogInterface
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

/**
 *
 * @author Igor Vilela
 * @since 21/03/2018
 */

/**
 * Retorna uma instância a partir da Intent de entrada e extrai o extra a partir do tipo [T] mapeado para a a entrada [key] sem valor default.
 * Ex:
 * class LoginActivity : Activity() {
 *     private val name by extra<String>("userName")
 * }
 */
inline fun <reified T> Activity.extra(key: String): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) {
        value
    } else {
        throw IllegalArgumentException("Couldn't find extra with key \"$key\" from type " +
                T::class.java.canonicalName)
    }
}

/**
 * Retorna uma instância a partir da Intent de entrada e extrai o extra a partir do tipo [T] mapeado para a a entrada [key], se não for encontrado o valor será retornado
 * o resultado da função [default]
 * Ex:
 * class LoginActivity : Activity() {
 *     private val name by extra<String>("userName")
 * }
 */
inline fun <reified T> Activity.extra(key: String, crossinline default: () -> T): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) value else default()
}

inline fun <reified T: Fragment> FragmentManager.findFragmentByTagOrNull(tag: String): T? {

    try {
        return this.findFragmentByTag(tag) as T
    } catch (e: Exception) {
        return null
    }
}

fun Activity.putKeyboardHidden(){
    this.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
}

fun Activity.requestPermission(permission: String, requestCode: Int) {

    //User already has the requested permission
    if(checkPermission(permission)) {
        return
    }

    ActivityCompat.requestPermissions(
        this,
        arrayOf(permission),
        requestCode)
}

fun Activity.hideSoftKeyboard() {

    try {

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0)

    } catch (e: NullPointerException) {
        Timber.w("NPE when hiding soft keyboard")
    }

}

fun Activity.showAlertDialog(
        title : String,
        message: String,
        positiveTextRes: Int,
        negativeTextRes: Int,
        positiveAction : ((DialogInterface) -> Unit)? = null,
        negativeAction: ((DialogInterface) -> Unit)? = null
) : AlertDialog {


    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)

    positiveAction?.let { builder.setPositiveButton(getString(positiveTextRes), { dialog, _ -> it(dialog) }) }
    negativeAction?.let { builder.setNegativeButton(getString(negativeTextRes), { dialog, _ -> it(dialog) }) }

    val alertDialog = builder.create()

    alertDialog.setOnShowListener { dialog ->
        val btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        btnNegative.setTextColor(getColorCompat(android.R.color.darker_gray))
    }

    alertDialog.show()

    if(this is LifecycleOwner) {
        lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun dismissDialogOnDestroy() {

                if (alertDialog.isShowing) {
                    Timber.d("Automatically dismissing alertDialog to avoid leaks")
                    alertDialog.dismiss()
                }
            }
        })
    }

    return alertDialog

}