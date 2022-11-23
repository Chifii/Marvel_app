package com.intermedia.marvel.home.domain.models

data class ResultsModel(
    // Only Characters Call
    val events: Events?,
    val name: String?,

    // Only Events call
    val characters: Characters?,
    val creators: Creators?,
    val end: String?,
    val next: Next,
    val previous: Previous?,
    val start: String?,
    val title: String?,

    // Commons
    val comics: Comics?,
    val description: String?,
    val id: Int,
    val modified: String?,
    val resourceURI: String?,
    val series: Series?,
    val stories: Stories?,
    val thumbnail: Thumbnail?,
    val urls: List<Url>?,
)

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)