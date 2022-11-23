package com.intermedia.marvel.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intermedia.marvel.base.domain.domain.Result
import com.intermedia.marvel.home.domain.models.DataWrapperModel
import com.intermedia.marvel.home.domain.models.ResultsModel
import com.intermedia.marvel.home.domain.service.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EventViewModel(
    val repository: HomeRepository = HomeRepository()
) : ViewModel() {
    private val scopeRecovery by lazy { CoroutineScope(Job() + Dispatchers.Main) }

    private val errorMDL: MutableLiveData<HttpException> = MutableLiveData()
    val error get() = errorMDL as LiveData<HttpException>

    private val eventsMDL: MutableLiveData<List<ResultsModel>> = MutableLiveData()
    val events get() = eventsMDL as LiveData<List<ResultsModel>>

    fun getEventsData() {
        scopeRecovery.launch {
            when (val response = repository.getEvents()) {
                is Result.Success -> sendEventsData(response.data)
                is Result.Error -> showError(response.exception)
            }
        }
    }

    fun sendEventsData(results: DataWrapperModel) {
        eventsMDL.value = results.data?.results
    }

    fun showError(exception: HttpException) {
        errorMDL.value = exception
    }
}