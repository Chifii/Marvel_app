package com.intermedia.marvel.detail.domain.models

import com.intermedia.marvel.home.domain.models.Comics
import com.intermedia.marvel.home.domain.models.Events
import com.intermedia.marvel.home.domain.models.Series
import com.intermedia.marvel.home.domain.models.Stories
import com.intermedia.marvel.home.domain.models.Thumbnail
import com.intermedia.marvel.home.domain.models.Url

data class DetailModel(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
)

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)