package com.igorvd.chuckfacts.domain.utils.extensions

import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * @author Igor Vilela
 * @since 08/01/2018
 */

/**
 * Converts strings in one of the following formats:
 * "2016-11-23T00:00:00", "2016-11-23 00:00:00" to a [Date]. If the String isn't valid, returns null
 */
fun String.toDate(): Date? {

    var newDate = intern()

    //removing the possible "T" tag from the date
    if (contains("T")) {
        newDate = newDate.replace("T", " ")
    }

    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    try {
        val dt = sdf.parse(newDate)
        return dt


    } catch (e: ParseException) {
        return null
    }
}

/**
 * Formats the date into [yyyy-MM-dd HH:mm:ss]
 */
fun Date.toDateString(): String {

    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Formats the date into [dd/MM/yyyy]
 */
fun Date.toPrettyDateString(): String {

    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Formats the hour into [hh:mm]
 */
fun Date.toHourAndMinute(): String {

    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Returns the date in the format dd/MM/yyyy
 */
fun Calendar.getDateAsString(context: Context, dateString: String): String {

    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var date: Date? = null
    try {
        date = sdf.parse(dateString)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    val dateFormat = android.text.format.DateFormat.getDateFormat(context)
    return dateFormat.format(date)
}

fun Calendar.setDate(year: Int, month: Int, day: Int) {
    set(Calendar.YEAR, year)
    set(Calendar.MONTH, month)
    set(Calendar.DAY_OF_MONTH, day)
}

/**
 * Sets the hour, minute, second and mills to 0
 */
fun Calendar.resetTime() {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}


/**
 * Sets the hour and minute of a date
 */
fun Calendar.setHourAndMinute(hour: Int, minute: Int) {
    set(Calendar.HOUR_OF_DAY, hour)
    set(Calendar.MINUTE, minute)
}

/**
 * Get the hour of a date in INT
 */
fun Calendar.getHour(): Int {
    return this.get(Calendar.HOUR_OF_DAY)
}

/**
 * Get the minute of a date in INT
 */
fun Calendar.getMinute(): Int {
    return this.get(Calendar.MINUTE)
}


/**
 * Returns the current datetime in the yyyy-MM-dd hh:mm:ss format
 */
fun getNowAsDateTimeString(): String = Calendar.getInstance().time.toDateString()

fun currentTimeInSeconds(): String {
    return (System.currentTimeMillis() / 1000).toString()
}