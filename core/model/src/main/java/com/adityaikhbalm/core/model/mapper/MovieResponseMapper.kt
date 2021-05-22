package com.adityaikhbalm.core.model.mapper

import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.core.model.response.MovieResponse

class MovieResponseMapper : EntityMapper<List<Movie>, MovieResponse> {
    override fun mapToEntity(domain: MovieResponse): List<Movie> {
        return domain.results ?: emptyList()
    }

    override fun mapFromEntity(entity: List<Movie>): MovieResponse {
        throw UnsupportedOperationException("Not Supported")
    }
}
