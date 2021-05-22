package com.adityaikhbalm.features.detail.viewmodel

import androidx.lifecycle.*
import com.adityaikhbalm.core.domain.usecase.cache.DeleteUseCase
import com.adityaikhbalm.core.domain.usecase.cache.FavoriteUseCase
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
    private val favoriteUseCase: FavoriteUseCase,
    private val insertUseCase: InsertUseCase,
    private val deleteUseCase: DeleteUseCase
) : ViewModel() {

    private var initialLoad = true
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

    fun getFavorite(id: Int) {
        viewModelScope.launch {
            favoriteUseCase.getFavorite(id)
                .flowOn(Dispatchers.IO).collectLatest {
                    if (initialLoad && it.id == 0) detailMovie(id)
                    else {
                        _detail.value = ResultState.Loading()
                        _detail.value = try {
                            ResultState.Success(it)
                        } catch (e: Throwable) {
                            ResultState.Error(e)
                        }
                    }
                    initialLoad = false
                }
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
