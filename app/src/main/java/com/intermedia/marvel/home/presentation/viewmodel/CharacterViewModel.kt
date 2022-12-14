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

class CharacterViewModel(
    val repository: HomeRepository = HomeRepository()
) : ViewModel() {
    private val scopeRecovery by lazy { CoroutineScope(Job() + Dispatchers.Main) }
    private var cumulativeData: MutableList<ResultsModel> = mutableListOf()
    private var offsetCount = 1

    private val errorMDL: MutableLiveData<HttpException> = MutableLiveData()
    val error get() = errorMDL as LiveData<HttpException>

    private val charactersMDL: MutableLiveData<List<ResultsModel>> = MutableLiveData()
    val characters get() = charactersMDL as LiveData<List<ResultsModel>>

    private val moreCharactersMDL: MutableLiveData<List<ResultsModel>> = MutableLiveData()
    val moreCharacters get() = moreCharactersMDL as LiveData<List<ResultsModel>>

    private val characterIdMDL: MutableLiveData<Int> = MutableLiveData()
    val characterId get() = characterIdMDL as LiveData<Int>

    fun getCharactersData() {
        scopeRecovery.launch {
            when (val response = repository.getCharacters()) {
                is Result.Success -> sendCharactersData(response.data)
                is Result.Error -> showError(response.exception)
            }
        }
    }

    fun getMoreCharacters(offset:Int) {
        scopeRecovery.launch {
            when (val response = repository.getMoreCharacters(offset*offsetCount)) {
                is Result.Success -> {
                    offsetCount++
                    sedMoreCharacters(response.data)
                }
                is Result.Error -> showError(response.exception)
            }
        }
    }

    fun sendCharactersData(results: DataWrapperModel) {
        results.data?.results?.forEach { cumulativeData.add(it) }
        charactersMDL.value = results.data?.results
    }

    fun sedMoreCharacters(results: DataWrapperModel) {
        results.data?.results?.forEach { cumulativeData.add(it) }
        moreCharactersMDL.value = results.data?.results
    }

    fun getCharacterId(position: Int) {
        characterIdMDL.value = cumulativeData[position].id
    }

    fun showError(exception: HttpException) {
        errorMDL.value = exception
    }
}