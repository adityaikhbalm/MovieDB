package com.adityaikhbalm.core.data.store

import com.adityaikhbalm.core.data.repository.MovieRemote
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.core.model.response.MovieResponse

class MovieRemoteDataStore(private val movieRemote: MovieRemote) : MovieRemoteStore {
    override suspend fun getBannerMovie(): MovieResponse {
        return movieRemote.getBannerMovie()
    }

    override suspend fun getPopularMovie(page: Int): MovieResponse {
        return movieRemote.getPopularMovie(page)
    }

    override suspend fun getComingMovie(): MovieResponse {
        return movieRemote.getComingMovie()
    }

    override suspend fun getDetailMovie(id: Int): Movie {
        return movieRemote.getDetailMovie(id)
    }

    override suspend fun searchMovie(query: String, page: Int): MovieResponse {
        return movieRemote.searchMovie(query, page)
    }
}
