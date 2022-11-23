package com.intermedia.marvel.detail.domain.service

import com.intermedia.marvel.home.domain.models.DataWrapperModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailService {
    @GET("characters/{id}")
    suspend fun getDetails(
        @Path("id") id: Int,
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): DataWrapperModel
}