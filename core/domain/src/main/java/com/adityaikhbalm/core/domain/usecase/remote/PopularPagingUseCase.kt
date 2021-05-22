package com.adityaikhbalm.core.domain.usecase.remote

import com.adityaikhbalm.core.domain.repository.MovieRepository

class PopularPagingUseCase(repository: MovieRepository) {
    val popularMovie = repository.popularPaging()
}
