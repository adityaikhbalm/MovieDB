package com.adityaikhbalm.core.domain.usecase.cache

import com.adityaikhbalm.core.domain.repository.MovieRepository

class FavoriteUseCase(private val repository: MovieRepository) {
    suspend fun getFavorite(id: Int) = repository.getFavorite(id)
}
