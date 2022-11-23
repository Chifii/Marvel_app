package com.intermedia.marvel.home.domain.models

import com.google.gson.annotations.SerializedName

data class DataWrapperModel(
    @SerializedName("code") val code: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("copyright") val copyright: String?,
    @SerializedName("attributionText") val attributionText: String?,
    @SerializedName("attributionHTML") val attributionHTML: String?,
    @SerializedName("data") val data: DataContainerModel?,
    @SerializedName("etag") val etag: String?
)