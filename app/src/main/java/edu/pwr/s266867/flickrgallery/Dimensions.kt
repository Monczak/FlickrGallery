package edu.pwr.s266867.flickrgallery

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDim = compositionLocalOf { Dimensions() }

data class Dimensions(
    val spacingAfterTitle: Dp = 16.dp,
    val spacingBetweenPhotos: Dp = 24.dp,
)
