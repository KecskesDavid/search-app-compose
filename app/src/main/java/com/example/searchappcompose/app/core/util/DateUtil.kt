package com.example.searchappcompose.app.core.util

import android.text.format.DateUtils
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

object DateUtil : DateUtils() {
    /**
     * Used for news card, when showing the timestamp
     * Returns a string like: 'Posted 13 day(s)' ago, or 'Posted 2
     * month(s) ago', depending on the date which is given as parameter.
     *
     * @param date: A simple string with the format: ISO_LOCAL_DATE_TIME
     * @return a string, with the format mentioned above
     */
    fun toNewsCardTimestamp(date: String): String {
        val period = Period.between(
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            LocalDate.now()
        )

        val daysFromDate = period.days
        val monthsFromDate = period.months

        return when (daysFromDate) {
            0 -> "Posted today"
            in 1..31 -> "Posted $daysFromDate day(s) ago"
            else -> "Posted $monthsFromDate month(s) ago"
        }
    }
}