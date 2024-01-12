package edu.pwr.s266867.flickrgallery.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.pwr.s266867.flickrgallery.data.FlickrRepo
import edu.pwr.s266867.flickrgallery.data.FlickrResponse
import edu.pwr.s266867.flickrgallery.utils.TimeoutException
import kotlinx.coroutines.launch

class FlickrGalleryViewModel(private val repo: FlickrRepo) : ViewModel() {
    val photos = mutableStateOf<FlickrResponse?>(null)
    val isLoading = mutableStateOf(false)
    val timedOut = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    val isGood get() = photos.value != null

    fun loadPhotos(tags: List<String>) {
        viewModelScope.launch {
            timedOut.value = false
            isLoading.value = true
            photos.value = null

            try {
                photos.value = repo.getPublicPhotos(tags)
            }
            catch (e: TimeoutException) {
                timedOut.value = true
            }
            catch (e: Exception) {
                error.value = e.message
            }
            isLoading.value = false
        }
    }
}