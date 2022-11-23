package com.intermedia.marvel.detail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intermedia.marvel.base.domain.domain.Result
import com.intermedia.marvel.detail.domain.service.DetailRepository
import com.intermedia.marvel.home.domain.models.DataWrapperModel
import com.intermedia.marvel.home.domain.models.ResultsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailViewModel(
    val repository: DetailRepository = DetailRepository()
) : ViewModel() {
    private val scopeRecovery by lazy { CoroutineScope(Job() + Dispatchers.Main) }

    private val errorMDL: MutableLiveData<HttpException> = MutableLiveData()
    val error get() = errorMDL as LiveData<HttpException>

    private val detailMDL: MutableLiveData<List<ResultsModel>> = MutableLiveData()
    val detail get() = detailMDL as LiveData<List<ResultsModel>>

    fun getDetailData(characterId: Int) {
        scopeRecovery.launch {
            when (val response = repository.getDetail(characterId)) {
                is Result.Success -> sendDetailData(response.data)
                is Result.Error -> showError(response.exception)
            }
        }
    }

    fun sendDetailData(results: DataWrapperModel) {
        detailMDL.value = results.data?.results
    }

    fun showError(exception: HttpException) {
        errorMDL.value = exception
    }
}