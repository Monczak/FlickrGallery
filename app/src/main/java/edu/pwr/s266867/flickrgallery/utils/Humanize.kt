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
    enum class DateAgoType {
        JUST_NOW,
        MINUTES,
        HOURS,
        DAYS,
        DATE
    }

    data class DateAgo(
        val type: DateAgoType,
        val value: Long?,
        val instant: Instant?,
    )

    fun timeAgo(instant: Instant, clock: Clock = Clock.System): DateAgo? {
        val duration = clock.now() - instant

        val minutes = duration.inWholeMinutes
        val hours = duration.inWholeHours
        val days = duration.inWholeDays

        return when {
            duration.isNegative() -> null
            minutes < 1 -> DateAgo(DateAgoType.JUST_NOW, null, null)
            minutes < 60 -> DateAgo(DateAgoType.MINUTES, minutes, null)
            hours < 24 -> DateAgo(DateAgoType.HOURS, hours, null)
            days < 7 -> DateAgo(DateAgoType.DAYS, days, null)
            else -> DateAgo(DateAgoType.DATE, null, instant)
        }
    }

    fun beautify(context: Context, dateAgo: DateAgo?): String = when (dateAgo?.type) {
        DateAgoType.JUST_NOW -> context.resources.getString(R.string.duration_just_now)
        DateAgoType.MINUTES -> context.resources.getString(R.string.time_ago_template, dateAgo.value, context.resources.getString(if (dateAgo.value == 1L) R.string.duration_minutes_singular else R.string.duration_minutes_plural))
        DateAgoType.HOURS -> context.resources.getString(R.string.time_ago_template, dateAgo.value, context.resources.getString(if (dateAgo.value == 1L) R.string.duration_hours_singular else R.string.duration_hours_plural))
        DateAgoType.DAYS -> context.resources.getString(R.string.time_ago_template, dateAgo.value, context.resources.getString(if (dateAgo.value == 1L) R.string.duration_days_singular else R.string.duration_days_plural))
        DateAgoType.DATE -> {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            ZonedDateTime.parse(dateAgo.instant.toString()).format(formatter)
        }

        else -> "unknown"
    }
}