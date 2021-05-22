package com.adityaikhbalm.core.domain.usecase.remote

import com.adityaikhbalm.core.domain.repository.MovieRepository

class DetailUseCase(private val repository: MovieRepository) {
    suspend fun getDetailMovie(id: Int) = repository.getDetailMovie(id)
}
