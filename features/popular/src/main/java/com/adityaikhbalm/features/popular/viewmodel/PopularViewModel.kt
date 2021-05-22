package com.adityaikhbalm.features.popular.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.adityaikhbalm.core.domain.usecase.remote.PopularPagingUseCase
import com.adityaikhbalm.core.domain.usecase.remote.SearchUseCase
import com.adityaikhbalm.core.model.response.Movie
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularViewModel(
    private val popularPagingUseCase: PopularPagingUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _popular = MutableLiveData<PagingData<Movie>>()
    val popular: LiveData<PagingData<Movie>> get() = _popular

    fun getPopular() {
        viewModelScope.launch {
            popularPagingUseCase.popularMovie.flow.cachedIn(viewModelScope).collectLatest {
                _popular.value = it
            }
        }
    }

    fun searchMovie(query: String) {
        viewModelScope.launch {
            searchUseCase.searchMovie(query).flow.cachedIn(viewModelScope).collectLatest {
                _popular.value = it
            }
        }
    }
}
