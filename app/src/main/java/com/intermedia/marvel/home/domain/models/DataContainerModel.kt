package com.intermedia.marvel.home.domain.models

import com.google.gson.annotations.SerializedName

data class DataContainerModel(
    @SerializedName("offset") val offset: Int?,
    @SerializedName("limit") val limit: Int?,
    @SerializedName("total") val total: Int?,
    @SerializedName("count") val count: Int?,
    @SerializedName("results") val results: List<ResultsModel>
)