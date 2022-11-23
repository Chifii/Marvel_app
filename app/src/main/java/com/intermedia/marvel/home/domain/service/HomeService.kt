package com.intermedia.marvel.home.domain.service

import com.intermedia.marvel.home.domain.models.DataWrapperModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: Int,
        @Query("limit") limit: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): DataWrapperModel

    @GET("events")
    suspend fun getEvents(
        @Query("ts") ts: Int,
        @Query("limit") limit: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): DataWrapperModel
}