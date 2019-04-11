package com.igorvd.chuckfacts.data.jokes.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.igorvd.chuckfacts.data.jokes.local.database.JokeEntity.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class JokeEntity(

    @PrimaryKey
    @ColumnInfo(name = FIELD_ID)
    var id: String = "",
    @ColumnInfo(name = FIELD_CATEGORIES)
    var categories: List<String> = emptyList(),
    @ColumnInfo(name = FIELD_URL)
    var url: String = "",
    @ColumnInfo(name = FIELD_VALUE)
    var value: String = "",
    @ColumnInfo(name = FIELD_QUERY)
    var query: String = ""

) {

    companion object {
        const val TABLE_NAME = "jokes"
        const val FIELD_ID = "id"
        const val FIELD_CATEGORIES = "category"
        const val FIELD_URL = "url"
        const val FIELD_VALUE = "value"
        const val FIELD_QUERY = "query"
    }

}