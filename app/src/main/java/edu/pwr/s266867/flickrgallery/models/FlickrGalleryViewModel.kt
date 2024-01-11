package edu.pwr.s266867.flickrgallery.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.pwr.s266867.flickrgallery.data.FlickrRepo
import edu.pwr.s266867.flickrgallery.data.FlickrResponse
import kotlinx.coroutines.launch

class FlickrGalleryViewModel(private val repo: FlickrRepo) : ViewModel() {
    val photos = mutableStateOf<FlickrResponse?>(null)
    val isLoading = mutableStateOf(false)

    fun loadPhotos(tags: List<String>) {
        viewModelScope.launch {
            isLoading.value = true
            photos.value = repo.getPublicPhotos(tags)
            isLoading.value = false
        }
    }
}