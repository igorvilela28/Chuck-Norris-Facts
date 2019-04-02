package com.igorvd.chuckfacts.features.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igorvd.chuckfacts.R
import kotlinx.android.synthetic.main.search_toolbar.*


class SearchJokeActivity : AppCompatActivity() {

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SearchJokeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            }
            return intent
        }

    }

    //**************************************************************************
    // region: LIFE CYCLE
    //**************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_joke)

        setupViews()

    }

    //endregion

    //**************************************************************************
    // region: INNER METHODS
    //**************************************************************************

    private fun setupViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    //endregion
}
