package com.igorvd.chuckfacts.data.jokes.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.igorvd.chuckfacts.data.utils.extensions.edit
import com.igorvd.chuckfacts.data.utils.extensions.sharedPreferences
import com.igorvd.chuckfacts.domain.jokes.repository.SearchHistoricRepository

class SearchHistoricRepositoryImpl  constructor(
    private val context: Context
) : SearchHistoricRepository {

    private val preferences by lazy { context.sharedPreferences(SEARCH_HISTORY_KEY) }
    private val gson by lazy { Gson() }

    companion object {
        const val SEARCH_HISTORY_KEY = "search_history"
    }

    override fun save(history: List<String>) {
        val json = gson.toJson(history)
        preferences.edit {
            it.putString(SEARCH_HISTORY_KEY, json)
        }
    }

    override fun retrieveAll(): List<String> {

        val categoriesJson = preferences.getString(SEARCH_HISTORY_KEY, "")
        if (categoriesJson.isNullOrBlank()) {
            return emptyList()
        }

        return gson.fromJson<List<String>>(categoriesJson, object : TypeToken<List<String>>() {}.type)

    }
}