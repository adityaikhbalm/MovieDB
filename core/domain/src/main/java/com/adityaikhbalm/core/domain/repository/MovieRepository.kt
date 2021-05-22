package com.adityaikhbalm.core.domain.repository

import androidx.paging.Pager
import com.adityaikhbalm.core.model.model.MovieEntity
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.libraries.abstraction.interactor.ResultState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getBannerMovie(): Flow<ResultState<List<Movie>>>

    suspend fun getPopularMovie(page: Int): Flow<ResultState<List<Movie>>>

    suspend fun getPagingPopular(page: Int): List<Movie>

    suspend fun getComingMovie(): Flow<ResultState<List<Movie>>>

    suspend fun getDetailMovie(id: Int): Flow<ResultState<Movie>>

    suspend fun searchMovie(query: String, page: Int): List<Movie>

    fun getAllFavorite(): Pager<Int, MovieEntity>

    suspend fun getFavorite(id: Int): Flow<Movie>

    fun searchFavorite(keyword: String): Pager<Int, MovieEntity>

    suspend fun insertFavorite(movie: Movie)

    suspend fun deleteFavorite(movie: Movie)

    fun popularPaging(): Pager<Int, Movie>

    fun searchPaging(query: String = "default"): Pager<Int, Movie>
}
