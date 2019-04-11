package com.igorvd.chuckfacts.data.jokes.local.database

import androidx.room.TypeConverter
import com.igorvd.chuckfacts.domain.utils.extensions.toStringWithSeparator

class ListStringConverter {

    @TypeConverter
    fun fromList(values: List<String>) = values.toStringWithSeparator()

    @TypeConverter
    fun toList(valuesString: String) = valuesString.split(",")

}