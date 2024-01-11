package edu.pwr.s266867.flickrgallery.data

import java.lang.RuntimeException

class FlickrRepo(private val flickrApi: FlickrApi) {
    suspend fun getPublicPhotos(tags: List<String>): FlickrResponse {
        try {
            return flickrApi.getPublicPhotos(tags)
        }
        catch (e: Exception) {
            throw RuntimeException("Something went wrong while fetching public photos: ${e.message}")
        }
    }
}