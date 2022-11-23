package com.intermedia.marvel.home.domain.models

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class Thumbnail(
    val extension: String,
    val path: String
)

data class Url(
    val type: String,
    val url: String
)

data class Item(
    val name: String,
    val resourceURI: String,
    val role: String?,
    val type: String?
)