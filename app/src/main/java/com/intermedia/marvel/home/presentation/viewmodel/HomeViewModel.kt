package com.intermedia.marvel.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intermedia.marvel.home.domain.service.HomeRepository
import com.intermedia.marvel.base.domain.domain.Result
import com.intermedia.marvel.home.domain.models.DataWrapperModel
import com.intermedia.marvel.home.domain.models.ResultsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel : ViewModel()