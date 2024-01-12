package edu.pwr.s266867.flickrgallery.models

import android.util.Log
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
                val filteredTags = tags.filter { it.isNotEmpty() }

                if (filteredTags.isEmpty())
                    photos.value = repo.getPublicPhotos(listOf("nature")) // Just needed a default tag, since untagged photos in the public feed are often... explicit
                else
                    photos.value = repo.getPublicPhotos(filteredTags)
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