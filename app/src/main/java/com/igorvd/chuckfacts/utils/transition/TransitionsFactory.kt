package com.igorvd.chuckfacts.utils.transition

import androidx.transition.AutoTransition
import androidx.transition.ChangeBounds
import androidx.transition.Transition

object TransitionsFactory {

    private val CHANGE_BOUNDS_DURATION = 150L
    private val FADE_OUT_DURATION = 150L

    /**
     * Creates a [ChangeBounds] transition that calls the
     * [Transition.TransitionListener.onTransitionEnd]
     * of the passing Listener when complete
     */
    fun changeBoundsWithActionOnEnd(
            duration: Long = CHANGE_BOUNDS_DURATION,
            action: () -> Unit): ChangeBounds {

        return transitionWithActionOnEnd(ChangeBounds(), duration, action)

    }

    /**
     * Creates a [AutoTransition] transition that calls the
     * [Transition.TransitionListener.onTransitionEnd]
     * of the passing Listener when complete
     */

    fun fadeOutWithActionOnEnd(
            duration: Long = FADE_OUT_DURATION,
            action: () -> Unit): AutoTransition {

        return transitionWithActionOnEnd(AutoTransition(), duration, action)

    }

    private fun <T : Transition> transitionWithActionOnEnd(
            transition: T,
            duration: Long,
            action: () -> Unit): T {

        val finishingAction = object : SimpleTransitionListener() {
            override fun onTransitionEnd(transition: Transition) {
                action()
            }
        }

        transition.apply {
            this.duration = duration
            addListener(finishingAction)
        }

        return transition

    }
}