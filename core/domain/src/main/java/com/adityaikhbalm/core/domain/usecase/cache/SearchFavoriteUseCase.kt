package com.adityaikhbalm.core.domain.usecase.cache

import com.adityaikhbalm.core.domain.repository.MovieRepository

class SearchFavoriteUseCase(private val repository: MovieRepository) {
    fun searchFavorite(keyword: String) = repository.searchFavorite(keyword)
}
