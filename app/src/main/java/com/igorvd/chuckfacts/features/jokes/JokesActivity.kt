package com.igorvd.chuckfacts.features.jokes

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.features.search.SearchJokeActivity
import kotlinx.android.synthetic.main.activity_jokes.*
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.igorvd.chuckfacts.utils.extensions.hideContent
import com.igorvd.chuckfacts.utils.extensions.showContent
import com.igorvd.chuckfacts.utils.transition.TransitionsFactory

class JokesActivity : AppCompatActivity() {

    private val paramsHolder: Pair<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams> by lazy {
        val originalParams = cvSearchBar.layoutParams as ConstraintLayout.LayoutParams
        val newParams = ConstraintLayout.LayoutParams(originalParams)
        newParams.setMargins(0, 0, 0, 0)
        originalParams to newParams
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)


        cvSearchBar.setOnClickListener {

            val transition = TransitionsFactory.fadeOutwithActionOnEnd {
                val intent = Intent(this@JokesActivity, SearchJokeActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                }
                startActivity(intent)
            }

            TransitionManager.beginDelayedTransition(cvSearchBar as CardView,
                    transition)

            cvSearchBar.layoutParams = paramsHolder.second
            ((cvSearchBar as CardView).getChildAt(0) as ViewGroup).hideContent()

        }
    }

    override fun onResume() {
        super.onResume()
        cvSearchBar.layoutParams = paramsHolder.first
        ((cvSearchBar as CardView).getChildAt(0) as ViewGroup).showContent()
    }
}