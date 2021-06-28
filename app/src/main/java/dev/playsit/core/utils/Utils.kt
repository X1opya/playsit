package dev.playsit.core.utils

import androidx.lifecycle.*
import dev.playsit.dto.FeedItem
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


private val parseFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'")
private val formatter = SimpleDateFormat("d MMMM")

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this
}

fun String?.toReadableDate(): String {
    if (this == null) return ""
    val date = parseFormat.parse(this)
    return SimpleDateFormat("MMM dd, yyyy").format(date)
}

fun Long?.millisecondsToTime(): String {
    this?.let {
        return when {
            it < 60L -> "0:$it"
            it == 60L -> "1:00"
            else -> {
                val minutes = TimeUnit.SECONDS.toMinutes(it)
                val seconds = it - minutes * 60
                val formattedSeconds = if (seconds < 10) "0$seconds" else seconds
                "${minutes}:${formattedSeconds}"
            }
        }
    } ?: return "0"
}

fun String?.toReleaseDate(): String {
    this?.let { dateString ->
        val date = parseFormat.parse(dateString)
        date?.let {
            return formatter.format(it)
        }
    } ?: return ""
}
