package com.adityaikhbalm.core.cache

import androidx.paging.PagingSource
import com.adityaikhbalm.core.data.repository.MovieCache
import com.adityaikhbalm.core.model.mapper.CastMapper
import com.adityaikhbalm.core.model.mapper.FavoriteMapper
import com.adityaikhbalm.core.model.mapper.SimilarMapper
import com.adityaikhbalm.core.model.mapper.TrailerMapper
import com.adityaikhbalm.core.model.model.*
import com.adityaikhbalm.core.model.response.Movie
import kotlinx.coroutines.flow.Flow

class MovieCacheImpl(
    private val movieDatabase: MovieDatabase,
    private val favoriteMapper: FavoriteMapper,
    private val castMapper: CastMapper,
    private val trailerMapper: TrailerMapper,
    private val similarMapper: SimilarMapper
) : MovieCache {
    override fun getAllFavorite(): PagingSource<Int, MovieEntity> {
        return movieDatabase.favoriteDAO().getAllFavorite()
    }

    override fun getFavorite(id: Int): Flow<MovieDetailEntity> {
        return movieDatabase.favoriteDAO().getFavorite(id)
    }

    override fun searchFavorite(keyword: String): PagingSource<Int, MovieEntity> {
        return movieDatabase.favoriteDAO().searchFavorite(keyword)
    }

    override suspend fun insertFavorite(movie: Movie) {
        val m = favoriteMapper.mapToEntity(movie)
        val c = castMapper.mapToEntity(movie)
        val t = trailerMapper.mapToEntity(movie)
        val s = similarMapper.mapToEntity(movie)

        movieDatabase.favoriteDAO().insertFavorite(m)
        insertCastList(c)
        insertTrailers(t)
        insertSimilar(s)
    }

    override suspend fun deleteFavorite(movie: Movie) {
        val m = favoriteMapper.mapToEntity(movie)
        movieDatabase.favoriteDAO().deleteFavorite(m)
    }

    private suspend fun insertCastList(cast: List<CastEntity>?) {
        if (!cast.isNullOrEmpty()) movieDatabase.castDAO().insertCast(cast)
    }

    private suspend fun insertTrailers(trailers: List<TrailerEntity>?) {
        if (!trailers.isNullOrEmpty()) movieDatabase.trailerDAO().insertTrailer(trailers)
    }

    private suspend fun insertSimilar(similar: List<SimilarEntity>?) {
        if (!similar.isNullOrEmpty()) movieDatabase.similarDAO().insertSimilar(similar)
    }
}
