package com.igorvd.chuckfacts.data.jokes.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.igorvd.chuckfacts.data.utils.extensions.edit
import com.igorvd.chuckfacts.data.utils.extensions.sharedPreferences

class JokeCategoryLocalDataSourceImpl  constructor(
    private val context: Context
) : JokeCategoryLocalDataSource {

    private val preferences by lazy { context.sharedPreferences(CATEGORY_KEY) }
    private val gson by lazy { Gson() }

    companion object {
        private const val CATEGORY_KEY = "joke_category"
    }

    override fun save(categories: List<String>) {
        val json = gson.toJson(categories)
        preferences.edit {
            it.putString(CATEGORY_KEY, json)
        }
    }

    override fun retrieveAll(): List<String> {

        val categoriesJson = preferences.getString(CATEGORY_KEY, "")
        if (categoriesJson.isNullOrBlank()) {
            return emptyList()
        }

        return gson.fromJson<List<String>>(categoriesJson, object : TypeToken<List<String>>() {}.type)

    }
}