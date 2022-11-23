package com.intermedia.marvel.home.domain.models

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class Next(
    val name: String,
    val resourceURI: String
)

data class Previous(
    val name: String,
    val resourceURI: String
)

