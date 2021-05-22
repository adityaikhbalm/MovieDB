package com.adityaikhbalm.core.domain.usecase.cache

import com.adityaikhbalm.core.domain.repository.MovieRepository
import com.adityaikhbalm.core.model.response.Movie

class InsertUseCase(private val repository: MovieRepository) {
    suspend fun insertFavorite(movie: Movie) = repository.insertFavorite(movie)
}
