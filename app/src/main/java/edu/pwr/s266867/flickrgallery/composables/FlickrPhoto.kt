package edu.pwr.s266867.flickrgallery.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edu.pwr.s266867.flickrgallery.LocalDim
import edu.pwr.s266867.flickrgallery.R
import edu.pwr.s266867.flickrgallery.data.FlickrItem
import edu.pwr.s266867.flickrgallery.data.FlickrMedia
import edu.pwr.s266867.flickrgallery.ui.theme.FlickrGalleryTheme
import edu.pwr.s266867.flickrgallery.utils.Humanize
import kotlinx.datetime.toInstant

@Composable
fun FlickrPhoto(modifier: Modifier = Modifier, photoData: FlickrItem) {
    val context = LocalContext.current
    Column {
        Row(
           horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = if (photoData.authorName != null) {
                    photoData.authorName!!
                } else {
                    stringResource(id = R.string.photo_unknown_author)
                },
                style = MaterialTheme.typography.labelSmall
            )

            Text(text = "•", style = MaterialTheme.typography.labelSmall)
            Text(text = Humanize.beautify(LocalContext.current, Humanize.timeAgo(photoData.published.toInstant())), style = MaterialTheme.typography.labelSmall)
            Text(text = "•", style = MaterialTheme.typography.labelSmall)
            Text(text = "Taken " + Humanize.beautify(LocalContext.current, Humanize.timeAgo(photoData.date_taken.toInstant())), style = MaterialTheme.typography.labelSmall)
        }

        Text(
            text = if (photoData.prettyTitle != null) {
                photoData.prettyTitle!!
            } else {
                stringResource(id = R.string.photo_untitled)
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(LocalDim.current.spacingAfterTitle))
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(photoData.link))
                    context.startActivity(intent)
                },
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(photoData.media.m)
                    .crossfade(true)
                    .build(),
                contentDescription = photoData.title,
                modifier = Modifier
                    .fillMaxWidth(),
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
            "nobody@flickr.com (\"John Photo\")",
            "133742069@N03",
            "tag1 tag2"
        ))
    }
}