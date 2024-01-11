package edu.pwr.s266867.flickrgallery.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import edu.pwr.s266867.flickrgallery.R
import edu.pwr.s266867.flickrgallery.data.FlickrItem
import edu.pwr.s266867.flickrgallery.data.FlickrMedia
import edu.pwr.s266867.flickrgallery.ui.theme.FlickrGalleryTheme

@Composable
fun FlickrPhoto(modifier: Modifier = Modifier, photoData: FlickrItem) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoData.media.m)
                    .crossfade(true)
                    .build(),
                contentDescription = photoData.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
fun FlickrPhotoPreview() {
    FlickrGalleryTheme {
        FlickrPhoto(photoData = FlickrItem(
            "Test Photo",
            "https://example.com",
            FlickrMedia("https://thispersondoesnotexist.com"),
            "1970-01-01T01:00:00Z",
            "Test Description",
            "2024-01-01T12:34:56Z",
            "John Photo",
            "133742069@N03",
            "tag1 tag2"
        ))
    }
}