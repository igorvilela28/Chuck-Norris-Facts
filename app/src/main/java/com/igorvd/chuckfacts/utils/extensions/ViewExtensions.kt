package com.igorvd.chuckfacts.utils.extensions

import android.view.View


/**
 *
 * @author Igor VIlela
 * @since 04/01/2018
 */

/**
 * Retorna ou define como GONE o estado de exibição de uma View.
 */
inline var View.isGone : Boolean
    get() = visibility == View.GONE
    set(value){
        visibility = if(value) View.GONE else View.VISIBLE
    }

/**
 * Retorna ou define como INVISIBLE o estado de exibição de uma View.
 */
inline var View.isInvisibile : Boolean
    get() = visibility == View.INVISIBLE
    set(value){
        visibility = if(value) View.INVISIBLE else View.VISIBLE
    }

/**
 * Retorna ou define como VISIBLE o estado de exibição de uma View.
 */
inline var View.isVisible : Boolean
    get() = visibility == View.VISIBLE
    set(value){
        visibility = if(value) View.VISIBLE else View.GONE
    }

