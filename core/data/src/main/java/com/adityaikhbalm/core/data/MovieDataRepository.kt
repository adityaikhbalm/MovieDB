package com.adityaikhbalm.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.adityaikhbalm.core.data.store.MovieCacheDataStore
import com.adityaikhbalm.core.data.store.MovieRemoteDataStore
import com.adityaikhbalm.core.data.store.PopularPagingSource
import com.adityaikhbalm.core.domain.repository.MovieRepository
import com.adityaikhbalm.core.model.mapper.FavoriteDetailMapper
import com.adityaikhbalm.core.model.mapper.MovieResponseMapper
import com.adityaikhbalm.core.model.model.MovieEntity
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.libraries.abstraction.extensions.fetch
import com.adityaikhbalm.libraries.abstraction.interactor.ResultState
import com.adityaikhbalm.libraries.utility.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MovieDataRepository(
    private val movieCacheDataStore: MovieCacheDataStore,
    private val movieRemoteDataStore: MovieRemoteDataStore,
    private val movieResponseMapper: MovieResponseMapper,
    private val favoriteDetailMapper: FavoriteDetailMapper
) : MovieRepository {
    override suspend fun getBannerMovie(): Flow<ResultState<List<Movie>>> {
        return fetch {
            movieResponseMapper.mapToEntity(
                movieRemoteDataStore.getBannerMovie()
            )
        }
    }

    override suspend fun getPopularMovie(page: Int): Flow<ResultState<List<Movie>>> {
        return fetch {
            movieResponseMapper.mapToEntity(
                movieRemoteDataStore.getPopularMovie(page)
            )
        }
    }

    override suspend fun getPagingPopular(page: Int): List<Movie> {
        return movieResponseMapper.mapToEntity(
            movieRemoteDataStore.getPopularMovie(page)
        )
    }

    override suspend fun getComingMovie(): Flow<ResultState<List<Movie>>> {
        return fetch {
            movieResponseMapper.mapToEntity(
                movieRemoteDataStore.getComingMovie()
            )
        }
    }

    override suspend fun getDetailMovie(id: Int): Flow<ResultState<Movie>> {
        val data = getFavorite(id).first()
        return fetch {
            data.takeIf { it.id != 0 } ?: movieRemoteDataStore.getDetailMovie(id)
        }
    }

    override suspend fun searchMovie(query: String, page: Int): List<Movie> {
        return movieResponseMapper.mapToEntity(
            movieRemoteDataStore.searchMovie(query, page)
        )
    }

    override fun getAllFavorite(): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = Constant.max_page, prefetchDistance = 2),
            pagingSourceFactory = { movieCacheDataStore.getAllFavorite() }
        )
    }

    override suspend fun getFavorite(id: Int): Flow<Movie> {
        return movieCacheDataStore.getFavorite(id).map {
            favoriteDetailMapper.mapFromEntity(it)
        }
    }

    override fun searchFavorite(keyword: String): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = Constant.min_page, prefetchDistance = 2),
            pagingSourceFactory = { movieCacheDataStore.searchFavorite(keyword) }
        )
    }

    override suspend fun insertFavorite(movie: Movie) {
        return movieCacheDataStore.insertFavorite(movie)
    }

    override suspend fun deleteFavorite(movie: Movie) {
        return movieCacheDataStore.deleteFavorite(movie)
    }

    override fun popularPaging(): Pager<Int, Movie> {
        return Pager(
            config = PagingConfig(pageSize = Constant.max_page, prefetchDistance = 2),
            pagingSourceFactory = {
                PopularPagingSource(
                    this,
                    1
                )
            }
        )
    }

    override fun searchPaging(query: String): Pager<Int, Movie> {
        return Pager(
            config = PagingConfig(pageSize = Constant.min_page, prefetchDistance = 2),
            pagingSourceFactory = {
                PopularPagingSource(
                    this,
                    2,
                    query
                )
            }
        )
    }
}
