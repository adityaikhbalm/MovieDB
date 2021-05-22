package com.adityaikhbalm.core.domain.usecase.remote

import com.adityaikhbalm.core.domain.repository.MovieRepository

class BannerUseCase(private val repository: MovieRepository) {
    suspend fun getBannerMovie() = repository.getBannerMovie()
}
