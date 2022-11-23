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
                    1,
                    100,
                    "9f274fd2b19012c9787bc1bd70f0c0aa",
                    "7d77ded4da4fc222a01abdb29a064e54"
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
                    1,
                    100,
                    "9f274fd2b19012c9787bc1bd70f0c0aa",
                    "7d77ded4da4fc222a01abdb29a064e54"
                )
                Result.Success(data)
            } catch (exception: HttpException) {
                Result.Error(exception)
            }
        }
    }
}