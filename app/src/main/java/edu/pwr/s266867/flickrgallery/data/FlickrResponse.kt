package edu.pwr.s266867.flickrgallery.data

data class FlickrResponse(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String,
    val items: List<FlickrItem>
)

data class FlickrItem(
    val title: String,
    val link: String,
    val media: FlickrMedia,
    val date_taken: String,
    val description: String,
    val published: String,
    val author: String,
    val author_id: String,
    val tags: String
) {
    val authorName: String? get() = Regex("""nobody@flickr\.com \("(.*)"\)""").find(author)?.groupValues?.get(1)
    val prettyTitle: String? get() = if (title.trim().isEmpty()) null else title
}

data class FlickrMedia(
    val m: String
)