package com.adityaikhbalm.features.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityaikhbalm.core.domain.usecase.cache.DeleteUseCase
import com.adityaikhbalm.core.domain.usecase.cache.InsertUseCase
import com.adityaikhbalm.core.domain.usecase.remote.DetailUseCase
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.libraries.abstraction.interactor.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailUseCase: DetailUseCase,
    private val insertUseCase: InsertUseCase,
    private val deleteUseCase: DeleteUseCase
) : ViewModel() {

    private val _detail = MutableLiveData<ResultState<Movie>>()
    val detail: LiveData<ResultState<Movie>>
        get() = _detail

    fun insertFavorite(movie: Movie) {
        viewModelScope.launch {
            movie.favorite = 1
            insertUseCase.insertFavorite(movie)
        }
    }

    fun deleteFavorite(movie: Movie) {
        viewModelScope.launch {
            deleteUseCase.deleteFavorite(movie)
        }
    }

    fun detailMovie(id: Int) {
        viewModelScope.launch {
            detailUseCase.getDetailMovie(id)
                .flowOn(Dispatchers.IO).collectLatest {
                    _detail.value = it
                }
        }
    }
}
