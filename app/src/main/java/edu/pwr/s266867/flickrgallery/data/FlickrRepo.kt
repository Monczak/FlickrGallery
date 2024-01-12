package edu.pwr.s266867.flickrgallery.data

import edu.pwr.s266867.flickrgallery.utils.TimeoutException
import java.lang.RuntimeException

class FlickrRepo(private val flickrApi: FlickrApi) {
    suspend fun getPublicPhotos(tags: List<String>): FlickrResponse {
        try {
            return flickrApi.getPublicPhotos(tags)
        }
        catch (e: Exception) {
            if (e.message?.contains("timeout") == true)
                throw TimeoutException()
            throw RuntimeException("Something went wrong while fetching public photos: ${e.message}")
        }
    }
}