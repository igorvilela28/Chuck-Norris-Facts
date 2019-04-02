package com.igorvd.chuckfacts.utils.extensions

import android.view.ViewGroup

/**
 *
 * @author Igor Vilela
 * @since 21/03/2018
 */

fun ViewGroup.hideContent() {
    for (i in 0 .. childCount) {
        getChildAt(i)?.isVisible = false
    }
}

fun ViewGroup.showContent() {
    for (i in 0 .. childCount) {
        getChildAt(i)?.isVisible = true
    }
}