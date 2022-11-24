package com.intermedia.marvel.home.domain.service

import com.intermedia.marvel.base.domain.service.BaseRepository
import com.intermedia.marvel.home.domain.models.DataWrapperModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import com.intermedia.marvel.base.domain.domain.Result
import java.math.BigInteger
import java.security.MessageDigest

class HomeRepository : BaseRepository<HomeService>(HomeService::class.java) {
    suspend fun getCharacters(): Result<DataWrapperModel> {
        return withContext(Dispatchers.IO) {
            try {
                val data = service.getCharacters(
                    TS, LIMIT, API_KEY, HASH
                )
                Result.Success(data)
            } catch (exception: HttpException) {
                Result.Error(exception)
            }
        }
    }

    suspend fun getMoreCharacters(offset: Int): Result<DataWrapperModel> {
        return withContext(Dispatchers.IO) {
            try {
                val data = service.getMoreCharacters(
                    TS, LIMIT, offset, API_KEY, HASH
                )
                Result.Success(data)
            } catch (exception: HttpException) {
                Result.Error(exception)
            }
        }
    }

    suspend fun getEvents(): Result<DataWrapperModel> {
        return withContext(Dispatchers.IO) {
            try {
                val data = service.getEvents(
                    TS, LIMIT, API_KEY, HASH
                )
                Result.Success(data)
            } catch (exception: HttpException) {
                Result.Error(exception)
            }
        }
    }

    suspend fun getMoreEvents(offset: Int): Result<DataWrapperModel> {
        return withContext(Dispatchers.IO) {
            try {
                val data = service.getMoreEvents(
                    TS, LIMIT, offset, API_KEY, HASH
                )
                Result.Success(data)
            } catch (exception: HttpException) {
                Result.Error(exception)
            }
        }
    }

    private companion object {
        const val API_KEY = "9f274fd2b19012c9787bc1bd70f0c0aa"
        const val HASH = "7d77ded4da4fc222a01abdb29a064e54"
        const val LIMIT = 15
        const val TS = 1
    }
}