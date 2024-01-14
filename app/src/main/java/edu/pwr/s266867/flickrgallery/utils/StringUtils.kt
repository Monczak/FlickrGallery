package edu.pwr.s266867.flickrgallery.utils

object StringUtils {
    fun abbreviate(str: String, maxLength: Int): String {
        val ellipsis = "…"
        return if (str.length - ellipsis.length >= maxLength) {
            str.take(maxLength - 1) + ellipsis
        }
        else {
            str
        }
    }
}