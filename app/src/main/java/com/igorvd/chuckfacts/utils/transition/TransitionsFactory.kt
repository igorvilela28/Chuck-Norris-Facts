package com.igorvd.chuckfacts.utils.transition

import androidx.transition.AutoTransition
import androidx.transition.ChangeBounds
import androidx.transition.Transition

object TransitionsFactory {

    private val CHANGE_BOUNDS_DURATION = 150L
    private val FADE_IN_DURATION = 100L

    /**
     * Creates a [ChangeBounds] transition that calls the
     * [Transition.TransitionListener.onTransitionEnd]
     * of the passing Listener when complete
     */
    fun changeBoundsWithActionOnEnd(duration: Long = CHANGE_BOUNDS_DURATION, action: () -> Unit): Transition {

        val finishingAction = object : SimpleTransitionListener() {
            override fun onTransitionEnd(transition: Transition) {
                action()
            }
        }

        val transition = ChangeBounds().apply {
            this.duration = duration
            addListener(finishingAction)
        }
        return transition
    }

    fun fadeInTransition(duration: Long = FADE_IN_DURATION): Transition {
        val transition = AutoTransition().apply {
            this.duration = duration
        }
        return transition
    }
}