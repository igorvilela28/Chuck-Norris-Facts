package com.igorvd.chuckfacts.utils.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout


/**
 *
 * @author Igor VIlela
 * @since 04/01/2018
 */

/**
 * Disables this button in case some of the given [editTexts] becomes empty. When it's first assigned,
 * it will check if some of the buttons is empty and automatically disable the button
 */
fun TextView.addButtonEnabledHook(vararg editTexts: EditText): TextWatcher {

     val setEnabledFunc = {
        var enabled = true
        for(editText in editTexts) {
            if(editText.text.toString().isEmpty()) {
                enabled = false
                break
            }
        }
        this@addButtonEnabledHook.isEnabled = enabled
    }

    //inicialmente configurando o botão baseado nos edit texts
    setEnabledFunc()

    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            //modificando quando ocorre mudança em algum editText
            setEnabledFunc()

        }
    }

    editTexts.forEach { editText -> editText.addTextChangedListener(textWatcher) }

    return textWatcher

}

/**
 * Shows the [errorMessage] if ![isValid]. Hide it otherwise
 */
fun TextInputLayout.addErrorValidation(errorMessage: String, isValid: () -> Boolean) {

    val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) { }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            this@addErrorValidation.isErrorEnabled = isValid()
            val error = if(isValid()) null else errorMessage
            this@addErrorValidation.error = error

        }

    }

    this.editText?.addTextChangedListener(textWatcher)


}

fun View.changeVisibility(visibility: Int) {

    if(this.visibility == visibility) {
        return
    }

    if (visibility == View.VISIBLE) {
        setVisibility(visibility)
        setAlpha(0.0f)

        animate()
                .alpha(1.0f)
                .setListener(null)
    } else {
        animate()
                .alpha(0.0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        setVisibility(visibility)
                    }
                })

    }

}

fun applyVisibilityToViews(visibility: Int, vararg views: View) =
    views.forEach { it.changeVisibility(visibility) }

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

fun View.onScreen(): Boolean {
    val visibleRect = Rect()
    this.getGlobalVisibleRect(visibleRect)
    return visibleRect.height() == this.height && visibleRect.width() == this.width
}

fun ImageView.setImageButtonEnabled(enabled: Boolean) {
    setEnabled(enabled)
    setAlpha(if (enabled) 1.0f else 0.3f)

    val originalIcon = getDrawable()
    val icon = if (enabled) originalIcon else originalIcon.convertDrawableToGrayScale()
    setImageDrawable(icon)

}

private fun Drawable.convertDrawableToGrayScale(): Drawable {
    val matrix = ColorMatrix()
    matrix.setSaturation(0f)
    val filter = ColorMatrixColorFilter(matrix)

    val mutated = mutate()
    mutated.colorFilter = filter

    return mutated
}