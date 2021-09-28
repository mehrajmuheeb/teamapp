package com.baseandroid.utils

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


fun String.parse(inputFormat: String = "hh:mm a", outputFormat: String = "HH:mm:ss.SSS"): String? {
    val sdf = SimpleDateFormat(inputFormat, Locale.ENGLISH);
    val dateObj = sdf.parse(this) ?: return null
    val timeZone = TimeZone.getTimeZone("UTC")
    return SimpleDateFormat(outputFormat).apply {
        this.timeZone = timeZone
    }.format(dateObj)
}

fun String.isBefore(compareWith: String, inputFormat: String = "hh:mm a"): Boolean {
    val sdf = SimpleDateFormat(inputFormat, Locale.getDefault())
    val dateBefore = sdf.parse(this)
    val dateAfter = sdf.parse(compareWith)

    return dateBefore.before(dateAfter)
}

//fun String.getDay(): String {
//    val localDate: LocalDate = DateTimeFormat.forPattern("dd-MM-yyyy").parseLocalDate(this)
//    return DateTimeFormat.forPattern("EEEE").print(localDate)
//}

fun getTimeZoneOffset(): String {
    val format = SimpleDateFormat("Z", Locale.getDefault()).format(Date())

    val sb = StringBuilder(format)
    sb.insert(3, ":")
    return sb.toString()
}

fun String.getDate(format: String = "dd-mm-yyyy"): Date? {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.parse(this)
}

fun String.parseToUTC(format: String = "dd-mm-yyyy"): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    val timeZone = TimeZone.getTimeZone("UTC")
    sdf.timeZone = timeZone
    val date = sdf.parse(this)

    return SimpleDateFormat(format, Locale.getDefault()).format(date)
}

fun getDateFromUTCTimestamp(
    mTimestamp: Long,
    mDateFormate: String?
): String? {
    var date: String? = null
    try {
        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        cal.timeInMillis = mTimestamp * 1000L
        date = DateFormat.format(mDateFormate, cal.timeInMillis).toString()
        val formatter = SimpleDateFormat(mDateFormate)
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val value = formatter.parse(date)
        val dateFormatter = SimpleDateFormat(mDateFormate)
        dateFormatter.timeZone = TimeZone.getDefault()
        date = dateFormatter.format(value)
        return date
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return date
}

