package com.adityaikhbalm.core.data.repository

import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.core.model.response.MovieResponse

interface MovieRemote {
    suspend fun getBannerMovie(): MovieResponse

    suspend fun getPopularMovie(page: Int): MovieResponse

    suspend fun getComingMovie(): MovieResponse

    suspend fun getDetailMovie(id: Int): Movie

    suspend fun searchMovie(query: String, page: Int): MovieResponse
}
