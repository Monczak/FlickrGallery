package edu.pwr.s266867.flickrgallery.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import edu.pwr.s266867.flickrgallery.R
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import kotlinx.datetime.toLocalDateTime
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Humanize {
    fun timeAgo(context: Context, instant: Instant): String {
        val duration = Clock.System.now() - instant

        val minutes = duration.inWholeMinutes
        val hours = duration.inWholeHours
        val days = duration.inWholeDays

        return when {
            minutes < 1 -> context.resources.getString(R.string.duration_just_now)
            minutes < 60 -> context.resources.getString(R.string.time_ago_template, minutes, context.resources.getString(if (minutes == 1L) R.string.duration_minutes_singular else R.string.duration_minutes_plural))
            hours < 24 -> context.resources.getString(R.string.time_ago_template, hours, context.resources.getString(if (hours == 1L) R.string.duration_hours_singular else R.string.duration_hours_plural))
            days < 7 -> context.resources.getString(R.string.time_ago_template, days, context.resources.getString(if (days == 1L) R.string.duration_days_singular else R.string.duration_days_plural))
            else -> {
                val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                ZonedDateTime.parse(instant.toString()).format(formatter)
            }
        }
    }
}