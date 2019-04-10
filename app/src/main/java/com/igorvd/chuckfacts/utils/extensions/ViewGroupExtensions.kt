package com.igorvd.chuckfacts.utils.extensions

import android.content.Context
import android.view.ViewGroup
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.igorvd.chuckfacts.R

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

fun ChipGroup.addChip(context: Context, text: String, onClick: (() -> Unit)? = null) {

    val chip = Chip(context).apply {
        setText(text)
        setTextAppearanceResource(R.style.ChipTextStyle)
        isClickable = onClick != null
        isFocusable = onClick != null
        onClick?.let { setOnClickListener { onClick() } }
    }

    this.addView(chip)

}