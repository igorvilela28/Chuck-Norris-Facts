package com.igorvd.chuckfacts.utils.extensions

import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import timber.log.Timber

/**
 * Contain extensions for Fragment and DialogFragment
 * @author Igor Vilela
 * @since 30/11/17
 */

//**************************************************************************
// SUPPORT DIALOG FRAGMENT EXTENSIONS
//**************************************************************************

/**
 * Checks if the FragmentDialog is added or visible
 */
fun DialogFragment.isAddedOrVisible() : Boolean = isAdded || isVisible

/**
 * Shows the FragmentDialog, avoiding show it multiple times
 */
fun DialogFragment.show(fragmentManager: FragmentManager) {

    //avoid show the same dialog multiple times
    if(isAddedOrVisible()) {
        return
    }

    try {

        this.show(fragmentManager, "dialog")

    } catch (e: IllegalStateException) {
        Timber.w("Illegal state exception when trying to show dialog fragment")
    }
}

/**
 * This is a step necessary to be able to show a radius in the FragmentDialogs. For it to work
 * you also should use the style R.style.dialogRoot
 */
fun DialogFragment.setDialogWithRadius() {

    dialog?.window?.decorView?.setBackgroundResource(android.R.color.transparent)
}

/**
 * Sets the FragmentDialog to be full screen. You MUST call this method in onStart to work
 */
fun DialogFragment.setFullScreen() {
    dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
}

/**
 * Sets the FragmentDialog width to Match Parent. You MUST call this method in onStart to work
 */
fun DialogFragment.setFullWidth() {
    dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
}

/**
 * Blocks the FragmentDialog to be dismiss
 */
fun DialogFragment.blockDismiss() {
    dialog?.setCancelable(false)
    dialog?.setCanceledOnTouchOutside(false)
    isCancelable = false
}