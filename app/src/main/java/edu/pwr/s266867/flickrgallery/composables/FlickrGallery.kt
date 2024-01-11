package edu.pwr.s266867.flickrgallery.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.pwr.s266867.flickrgallery.models.FlickrGalleryViewModel

@Composable
fun FlickrGallery(modifier: Modifier = Modifier, viewModel: FlickrGalleryViewModel, startPadding: Dp = 0.dp) {
    val photos = viewModel.photos.value

    if (viewModel.isLoading.value) {
        CircularProgressIndicator(modifier = modifier)
    }
    else {
        LazyColumn(modifier = modifier) {
            items(photos!!.items.size) { index ->
                if (index == 0) {
                    Spacer(
                        modifier = modifier
                            .height(startPadding)
                    )
                }

                FlickrPhoto(photoData = photos.items[index])
            }
        }
    }
}