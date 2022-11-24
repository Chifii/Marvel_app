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
    private var cumulativeData: MutableList<ResultsModel> = mutableListOf()
    private var offsetCount = 1

    private val errorMDL: MutableLiveData<HttpException> = MutableLiveData()
    val error get() = errorMDL as LiveData<HttpException>

    private val eventsMDL: MutableLiveData<List<ResultsModel>> = MutableLiveData()
    val events get() = eventsMDL as LiveData<List<ResultsModel>>

    private val moreEventsMDL: MutableLiveData<List<ResultsModel>> = MutableLiveData()
    val moreEvents get() = moreEventsMDL as LiveData<List<ResultsModel>>

    fun getEventsData() {
        scopeRecovery.launch {
            when (val response = repository.getEvents()) {
                is Result.Success -> sendEventsData(response.data)
                is Result.Error -> showError(response.exception)
            }
        }
    }

    fun getEventsData(offset: Int) {
        scopeRecovery.launch {
            when (val response = repository.getMoreEvents(offset * offsetCount)) {
                is Result.Success -> {
                    offsetCount++
                    sendMoreEvents(response.data)
                }
                is Result.Error -> showError(response.exception)
            }
        }
    }

    private fun sendMoreEvents(results: DataWrapperModel) {
        results.data?.results?.forEach { cumulativeData.add(it) }
        moreEventsMDL.value = results.data?.results
    }

    private fun sendEventsData(results: DataWrapperModel) {
        eventsMDL.value = results.data?.results
    }

    private fun showError(exception: HttpException) {
        errorMDL.value = exception
    }
}