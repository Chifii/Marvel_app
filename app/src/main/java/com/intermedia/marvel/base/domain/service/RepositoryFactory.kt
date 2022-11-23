package com.intermedia.marvel.base.domain.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level


object RepositoryFactory {

    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(interceptor)

}