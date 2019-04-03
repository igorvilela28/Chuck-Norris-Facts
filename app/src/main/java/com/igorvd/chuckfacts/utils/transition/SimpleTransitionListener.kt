package com.igorvd.chuckfacts.utils.transition

import androidx.transition.Transition

abstract class SimpleTransitionListener : Transition.TransitionListener {
    override fun onTransitionStart(transition: Transition) {
        // do nothing
    }

    override fun onTransitionPause(transition: Transition) {
        // do nothing
    }

    override fun onTransitionResume(transition: Transition) {
        // do nothing
    }

    override fun onTransitionEnd(transition: Transition) {
        // do nothing
    }

    override fun onTransitionCancel(transition: Transition) {
        // do nothing
    }
}