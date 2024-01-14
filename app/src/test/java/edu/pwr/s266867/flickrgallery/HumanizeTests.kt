package edu.pwr.s266867.flickrgallery

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import edu.pwr.s266867.flickrgallery.utils.Humanize
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class HumanizeTests {

    class FixedClock(private val fixedInstant: Instant): Clock {
        override fun now(): Instant = fixedInstant
    }

    private fun Clock.Companion.fixed(fixedInstant: Instant): Clock = FixedClock(fixedInstant)

    private val fixedClock: Clock = Clock.fixed(Instant.parse("2024-01-01T12:00:00Z"))

    @Test
    fun `date 1 second before should be just now`()  {
        val dateAgo = Humanize.timeAgo(Instant.parse("2024-01-01T11:59:59Z"), fixedClock)
        assertEquals(Humanize.DateAgoType.JUST_NOW, dateAgo.type)
    }

    @Test
    fun `date 60 seconds before should be 1 minute ago`()  {
        val dateAgo = Humanize.timeAgo(Instant.parse("2024-01-01T11:59:00Z"), fixedClock)
        assertEquals(Humanize.DateAgoType.MINUTES, dateAgo.type)
        assertEquals(1L, dateAgo.value)
    }

    @Test
    fun `date 60 minutes before should be 1 hour ago`()  {
        val dateAgo = Humanize.timeAgo(Instant.parse("2024-01-01T11:00:00Z"), fixedClock)
        assertEquals(Humanize.DateAgoType.HOURS, dateAgo.type)
        assertEquals(1L, dateAgo.value)
    }

    @Test
    fun `date 24 hours before should be 1 day ago`()  {
        val dateAgo = Humanize.timeAgo(Instant.parse("2023-12-31T12:00:00Z"), fixedClock)
        assertNotNull(dateAgo)
        assertEquals(Humanize.DateAgoType.DAYS, dateAgo.type)
        assertEquals(1L, dateAgo.value)
    }

    @Test
    fun `date 7 days before should be the date verbatim`()  {
        val date = Instant.parse("2023-12-25T12:00:00Z")
        val dateAgo = Humanize.timeAgo(date, fixedClock)
        assertEquals(Humanize.DateAgoType.DATE, dateAgo.type)
        assertEquals(date, dateAgo.instant)
    }

    @Test
    fun `date in the future should be the date verbatim`()  {
        val date = Instant.parse("2025-01-01T12:00:00Z")
        val dateAgo = Humanize.timeAgo(date, fixedClock)
        assertEquals(Humanize.DateAgoType.DATE, dateAgo.type)
        assertEquals(date, dateAgo.instant)
    }
}