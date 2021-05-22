package com.adityaikhbalm.core.data.store

import androidx.paging.PagingSource
import com.adityaikhbalm.core.data.repository.MovieCache
import com.adityaikhbalm.core.model.model.MovieDetailEntity
import com.adityaikhbalm.core.model.model.MovieEntity
import com.adityaikhbalm.core.model.response.Movie
import kotlinx.coroutines.flow.Flow

class MovieCacheDataStore(
    private val movieCache: MovieCache
) : MovieCacheStore {
    override fun getAllFavorite(): PagingSource<Int, MovieEntity> {
        return movieCache.getAllFavorite()
    }

    override fun getFavorite(id: Int): Flow<MovieDetailEntity> {
        return movieCache.getFavorite(id)
    }

    override fun searchFavorite(keyword: String): PagingSource<Int, MovieEntity> {
        return movieCache.searchFavorite(keyword)
    }

    override suspend fun insertFavorite(movie: Movie) {
        movieCache.insertFavorite(movie)
    }

    override suspend fun deleteFavorite(movie: Movie) {
        movieCache.deleteFavorite(movie)
    }
}
