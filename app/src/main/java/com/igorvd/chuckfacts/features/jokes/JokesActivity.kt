package com.igorvd.chuckfacts.features.jokes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.features.search.SearchJokeActivity
import kotlinx.android.synthetic.main.activity_jokes.*

class JokesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        cvSearchBar.setOnClickListener {
            val intent = Intent(this@JokesActivity, SearchJokeActivity::class.java)
            startActivity(intent)
        }
    }
}
