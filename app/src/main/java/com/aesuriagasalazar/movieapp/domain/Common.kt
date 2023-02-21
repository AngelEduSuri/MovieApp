package com.aesuriagasalazar.movieapp.domain

import java.text.NumberFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun String.toYearString(): String = this.split("-")[0]

fun Int.toDurationString(): String {
    val hours = TimeUnit.MINUTES.toHours(this.toLong())
    val remain = this - TimeUnit.HOURS.toMinutes(hours)
    return "${hours}h ${remain}min"
}

fun String.toLanguageConvert(): String = when (this) {
    "en" -> "English"
    "es" -> "Spanish"
    else -> {
        "No language specified"
    }
}

fun Long.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    return numberFormat.format(this)
}
