package com.igorvd.chuckfacts.utils.extensions

import com.igorvd.chuckfacts.BuildConfig
import timber.log.Timber

/**
 * Contains extensions for the Exception class and sub-classes
 * @author Igor Vilela
 * @since 16/10/17
 */


/**
 * Throws the exception if [BuildConfig.DEBUG] or log it in our crash-report tool otherwise.
 * This is useful in order to find bugs fast while development
 */
fun Exception.throwOrLog() {

    if(BuildConfig.DEBUG) {
        Timber.e(this, "Throw exception catched in DEBUG mode")
        throw this
    } else {

        //TODO: add some crash-reporting tool log for the exception
    }
}