package com.adityaikhbalm.core.data.repository

import androidx.paging.PagingSource
import com.adityaikhbalm.core.model.model.MovieDetailEntity
import com.adityaikhbalm.core.model.model.MovieEntity
import com.adityaikhbalm.core.model.response.Movie
import kotlinx.coroutines.flow.Flow

interface MovieCache {
    fun getAllFavorite(): PagingSource<Int, MovieEntity>

    fun getFavorite(id: Int): Flow<MovieDetailEntity>

    fun searchFavorite(keyword: String): PagingSource<Int, MovieEntity>

    suspend fun insertFavorite(movie: Movie)

    suspend fun deleteFavorite(movie: Movie)
}
