package com.igorvd.chuckfacts.utils.extensions

import com.igorvd.chuckfacts.BuildConfig


/**
 *
 * @author Igor Vilela
 * @since 12/07/2018
 */



inline fun runWhenDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}