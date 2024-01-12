// Testing devices:
// Virtual: Pixel 3a (API 34)
// Physical: OnePlus 7T Pro (Android 12)

package edu.pwr.s266867.flickrgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.gson.GsonBuilder
import edu.pwr.s266867.flickrgallery.composables.FlickrGallery
import edu.pwr.s266867.flickrgallery.composables.FlickrGalleryScreen
import edu.pwr.s266867.flickrgallery.data.FlickrApi
import edu.pwr.s266867.flickrgallery.data.FlickrRepo
import edu.pwr.s266867.flickrgallery.models.FlickrGalleryViewModel
import edu.pwr.s266867.flickrgallery.ui.theme.FlickrGalleryTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FlickrGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlickrGalleryScreen()
                }
            }
        }
    }
}
