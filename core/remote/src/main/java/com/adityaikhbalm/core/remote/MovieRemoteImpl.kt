package com.adityaikhbalm.core.remote

import com.adityaikhbalm.core.data.repository.MovieRemote
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.core.model.response.MovieResponse
import com.adityaikhbalm.core.remote.service.MovieApiService

class MovieRemoteImpl(private val movieApiService: MovieApiService) : MovieRemote {
    override suspend fun getBannerMovie(): MovieResponse {
        return movieApiService.getBannerMovie()
    }

    override suspend fun getPopularMovie(page: Int): MovieResponse {
        return movieApiService.getPopularMovie(page)
    }

    override suspend fun getComingMovie(): MovieResponse {
        return movieApiService.getComingMovie()
    }

    override suspend fun getDetailMovie(id: Int): Movie {
        return movieApiService.getDetailMovie(id)
    }

    override suspend fun searchMovie(query: String, page: Int): MovieResponse {
        return movieApiService.searchMovie(query, page)
    }
}
