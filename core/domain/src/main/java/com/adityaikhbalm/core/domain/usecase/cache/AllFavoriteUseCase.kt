package com.adityaikhbalm.core.domain.usecase.cache

import com.adityaikhbalm.core.domain.repository.MovieRepository

class AllFavoriteUseCase(repository: MovieRepository) {
    val favoriteMovie = repository.getAllFavorite()
}
