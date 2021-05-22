package com.adityaikhbalm.core.domain.usecase.remote

import com.adityaikhbalm.core.domain.repository.MovieRepository

class PopularUseCase(private val repository: MovieRepository) {
    suspend fun getPopularMovie(page: Int) = repository.getPopularMovie(page)
}
