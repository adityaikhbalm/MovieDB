package com.adityaikhbalm.core.domain.usecase.remote

import com.adityaikhbalm.core.domain.repository.MovieRepository

class ComingUseCase(private val repository: MovieRepository) {
    suspend fun getComingMovie() = repository.getComingMovie()
}
