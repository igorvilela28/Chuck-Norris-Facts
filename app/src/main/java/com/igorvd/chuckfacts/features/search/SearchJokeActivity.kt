package com.igorvd.chuckfacts.features.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igorvd.chuckfacts.R
import kotlinx.android.synthetic.main.search_toolbar.*
import timber.log.Timber

class SearchJokeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_joke)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }
}
