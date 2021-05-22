package com.adityaikhbalm.core.model.mapper

import com.adityaikhbalm.core.model.model.GenreEntity
import com.adityaikhbalm.core.model.model.MovieEntity
import com.adityaikhbalm.core.model.response.Genre
import com.adityaikhbalm.core.model.response.Movie

class FavoriteMapper : EntityMapper<MovieEntity, Movie> {
    override fun mapToEntity(domain: Movie): MovieEntity {
        val genre: MutableList<GenreEntity> = mutableListOf()
        domain.genres?.forEach {
            genre.add(GenreEntity(id = it.id, name = it.name))
        }

        return MovieEntity(
            id = domain.id,
            backdropPath = domain.backdropPath,
            genres = genre,
            overview = domain.overview,
            posterPath = domain.posterPath,
            releaseDate = domain.releaseDate,
            runtime = domain.runtime,
            title = domain.title,
            favorite = domain.favorite
        )
    }

    override fun mapFromEntity(entity: MovieEntity): Movie {
        val genre: MutableList<Genre> = mutableListOf()
        entity.genres?.forEach {
            genre.add(Genre(id = it.id, name = it.name))
        }

        return Movie(
            backdropPath = entity.backdropPath,
            genres = genre,
            id = entity.id,
            overview = entity.overview,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            runtime = entity.runtime,
            title = entity.title,
            favorite = entity.favorite
        )
    }
}
