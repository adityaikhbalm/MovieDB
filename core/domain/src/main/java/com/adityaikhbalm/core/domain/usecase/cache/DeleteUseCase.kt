package com.adityaikhbalm.core.domain.usecase.cache

import com.adityaikhbalm.core.domain.repository.MovieRepository
import com.adityaikhbalm.core.model.response.Movie

class DeleteUseCase(private val repository: MovieRepository) {
    suspend fun deleteFavorite(movie: Movie) = repository.deleteFavorite(movie)
}
