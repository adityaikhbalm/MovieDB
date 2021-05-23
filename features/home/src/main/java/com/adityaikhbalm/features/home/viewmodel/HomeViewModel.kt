package com.adityaikhbalm.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityaikhbalm.core.domain.usecase.remote.BannerUseCase
import com.adityaikhbalm.core.domain.usecase.remote.ComingUseCase
import com.adityaikhbalm.core.domain.usecase.remote.PopularUseCase
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.libraries.abstraction.interactor.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bannerUseCase: BannerUseCase,
    private val popularUseCase: PopularUseCase,
    private val comingUseCase: ComingUseCase,
) : ViewModel() {

    private val _banner = MutableLiveData<ResultState<List<Movie>>>()
    val banner: LiveData<ResultState<List<Movie>>> get() = _banner

    private val _popular = MutableLiveData<ResultState<List<Movie>>>()
    val popular: LiveData<ResultState<List<Movie>>> get() = _popular

    private val _coming = MutableLiveData<ResultState<List<Movie>>>()
    val coming: LiveData<ResultState<List<Movie>>> get() = _coming

    fun initialLoad() {
        bannerLoad()
        popularLoad()
        comingLoad()
    }

    fun bannerLoad() {
        viewModelScope.launch {
            bannerUseCase.getBannerMovie()
                .flowOn(Dispatchers.IO).collectLatest {
                    _banner.value = it
                }
        }
    }

    fun popularLoad() {
        viewModelScope.launch {
            popularUseCase.getPopularMovie(1)
                .flowOn(Dispatchers.IO).collectLatest {
                    _popular.value = it
                }
        }
    }

    fun comingLoad() {
        viewModelScope.launch {
            comingUseCase.getComingMovie()
                .flowOn(Dispatchers.IO).collectLatest {
                    _coming.value = it
                }
        }
    }
}
