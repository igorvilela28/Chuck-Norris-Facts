package com.igorvd.chuckfacts.utils.transition

import androidx.transition.AutoTransition
import androidx.transition.Transition

object TransitionsFactory {

    private val FADE_OUT_DURATION = 150L
    private val FADE_IN_DURATION = 100L

    /**
     * Creates a AutoTransition that calls the
     * [Transition.TransitionListener.onTransitionEnd]
     * of the passing Listener when complete
     */
    fun fadeOutwithActionOnEnd(duration: Long = FADE_OUT_DURATION, action: () -> Unit): Transition {

        val finishingAction = object : SimpleTransitionListener() {
            override fun onTransitionEnd(transition: Transition) {
                action()
            }
        }

        val transition = AutoTransition().apply {
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