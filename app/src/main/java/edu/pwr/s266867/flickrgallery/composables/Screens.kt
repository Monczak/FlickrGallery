package edu.pwr.s266867.flickrgallery.composables

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.gson.GsonBuilder
import edu.pwr.s266867.flickrgallery.Constants
import edu.pwr.s266867.flickrgallery.data.FlickrApi
import edu.pwr.s266867.flickrgallery.data.FlickrRepo
import edu.pwr.s266867.flickrgallery.models.FlickrGalleryViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlickrGalleryScreen(modifier: Modifier = Modifier) {
    val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.FLICKR_API_BASE)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addConverterFactory(ScalarsConverterFactory.create())
        .build();

    val flickrApi = retrofit.create<FlickrApi>()
    val repo = FlickrRepo(flickrApi)
    val viewModel = remember { FlickrGalleryViewModel(repo) }

    var query by rememberSaveable { mutableStateOf("") }
    var searchActive by rememberSaveable { mutableStateOf(false) }

    viewModel.loadPhotos(emptyList())

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxWidth()
            ) {
                SearchBar(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = {
                        searchActive = false
                        viewModel.loadPhotos(it.split(" "))
                    },
                    active = searchActive,
                    onActiveChange = {
                        searchActive = it
                    },
                    placeholder = { Text("Search by tags") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                )
                {

                }
            }
        },
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .padding(PaddingValues(start = 16.dp, end = 16.dp))
            ) {
                FlickrGallery(
                    viewModel = viewModel,
                    topPadding = innerPadding.calculateTopPadding()
                )
            }
        }
    )
}