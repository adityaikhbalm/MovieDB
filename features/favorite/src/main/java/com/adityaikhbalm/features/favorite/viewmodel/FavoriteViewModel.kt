package com.adityaikhbalm.features.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.adityaikhbalm.core.domain.usecase.cache.AllFavoriteUseCase
import com.adityaikhbalm.core.domain.usecase.cache.DeleteUseCase
import com.adityaikhbalm.core.domain.usecase.cache.SearchFavoriteUseCase
import com.adityaikhbalm.core.model.mapper.FavoriteMapper
import com.adityaikhbalm.core.model.response.Movie
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val allFavoriteUseCase: AllFavoriteUseCase,
    private val searchFavoriteUseCase: SearchFavoriteUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val favoriteMapper: FavoriteMapper
) : ViewModel() {

    private val _favorite = MutableLiveData<PagingData<Movie>>()
    val favorite: LiveData<PagingData<Movie>> get() = _favorite

    fun getAllFavorite() {
        viewModelScope.launch {
            allFavoriteUseCase.favoriteMovie.flow.cachedIn(viewModelScope).collectLatest { data ->
                _favorite.value = data.map {
                    favoriteMapper.mapFromEntity(it)
                }
            }
        }
    }

    fun searchFavorite(keyword: String) {
        viewModelScope.launch {
            searchFavoriteUseCase.searchFavorite(keyword).flow.cachedIn(viewModelScope)
                .collectLatest { data ->
                    _favorite.value = data.map {
                        favoriteMapper.mapFromEntity(it)
                    }
                }
        }
    }

    fun deleteFavorite(movie: Movie) {
        viewModelScope.launch {
            deleteUseCase.deleteFavorite(movie)
        }
    }
}
