package com.intermedia.marvel.detail.domain.service

import com.intermedia.marvel.base.domain.domain.Result
import com.intermedia.marvel.base.domain.service.BaseRepository
import com.intermedia.marvel.home.domain.models.DataWrapperModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class DetailRepository : BaseRepository<DetailService>(DetailService::class.java) {
    suspend fun getDetail(characterId: Int): Result<DataWrapperModel> {
        return withContext(Dispatchers.IO) {
            try {
                val data = service.getDetails(
                    characterId,
                    1,
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