package com.adityaikhbalm.core.domain.usecase.remote

import com.adityaikhbalm.core.domain.repository.MovieRepository

class SearchUseCase(private val repository: MovieRepository) {
    fun searchMovie(query: String) = repository.searchPaging(query)
}
