package edu.pwr.s266867.flickrgallery.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.pwr.s266867.flickrgallery.LocalDim
import edu.pwr.s266867.flickrgallery.R
import edu.pwr.s266867.flickrgallery.models.FlickrGalleryViewModel

@Composable
fun FlickrGalleryError(header: String, desc: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = header,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = desc,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FlickrGallery(modifier: Modifier = Modifier, viewModel: FlickrGalleryViewModel, topPadding: Dp = 0.dp) {
    val photos = viewModel.photos.value

    if (!viewModel.isLoading.value && viewModel.isGood) {
        LazyColumn(modifier = modifier) {
            items(photos!!.items.size) { index ->
                if (index == 0) {
                    Spacer(
                        modifier = modifier
                            .height(topPadding + LocalDim.current.spacingBetweenPhotos)
                    )
                }

                FlickrPhoto(photoData = photos.items[index])

                Spacer(modifier = Modifier.height(LocalDim.current.spacingBetweenPhotos))
            }
        }
    }
    else {
        Box(modifier = modifier
            .padding(PaddingValues(top = topPadding, start = 16.dp, end = 16.dp))
            .fillMaxSize()
        ) {
            if (viewModel.timedOut.value) {
                FlickrGalleryError(
                    header = stringResource(id = R.string.timed_out_header),
                    desc = stringResource(id = R.string.timed_out_desc)
                )
            }
            else if (viewModel.error.value != null) {
                FlickrGalleryError(
                    header = stringResource(id = R.string.fetch_error_header),
                    desc = viewModel.error.value!!
                )
            }
            else {
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center)
                )
            }
        }
    }
}