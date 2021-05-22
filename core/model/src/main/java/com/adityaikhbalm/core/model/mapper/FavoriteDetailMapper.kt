package com.adityaikhbalm.core.model.mapper

import com.adityaikhbalm.core.model.model.*
import com.adityaikhbalm.core.model.response.*

class FavoriteDetailMapper : EntityMapper<MovieDetailEntity?, Movie> {
    override fun mapToEntity(domain: Movie): MovieDetailEntity {
        val genre: MutableList<GenreEntity> = mutableListOf()
        domain.genres?.forEach {
            genre.add(GenreEntity(id = it.id, name = it.name))
        }

        val favorite = MovieEntity(
            backdropPath = domain.backdropPath,
            genres = genre,
            id = domain.id,
            overview = domain.overview,
            posterPath = domain.posterPath,
            releaseDate = domain.releaseDate,
            runtime = domain.runtime,
            title = domain.title,
            favorite = domain.favorite
        )

        val cast: MutableList<CastEntity> = mutableListOf()
        domain.credit?.cast?.forEach {
            cast.add(
                CastEntity(
                    id = it.id,
                    name = it.name,
                    profilePath = it.profilePath,
                    order = it.order
                )
            )
        }

        val trailer: MutableList<TrailerEntity> = mutableListOf()
        domain.trailer?.results?.forEach {
            trailer.add(
                TrailerEntity(
                    id = it.id,
                    key = it.key,
                    name = it.name,
                    site = it.site
                )
            )
        }

        val similar: MutableList<SimilarEntity> = mutableListOf()
        domain.similar?.results?.forEach {
            similar.add(
                SimilarEntity(
                    id = it.id,
                    title = it.title,
                    posterPath = it.posterPath
                )
            )
        }

        return MovieDetailEntity(
            favorite = favorite,
            castList = cast,
            trailerList = trailer,
            similarList = similar
        )
    }

    override fun mapFromEntity(entity: MovieDetailEntity?): Movie {
        val genre: MutableList<Genre> = mutableListOf()
        entity?.favorite?.genres?.forEach {
            genre.add(Genre(id = it.id, name = it.name))
        }

        val credit: MutableList<Cast> = mutableListOf()
        entity?.castList?.sortedBy { it.order }?.forEach {
            credit.add(
                Cast(
                    id = it.id,
                    name = it.name,
                    profilePath = it.profilePath,
                    order = it.order
                )
            )
        }

        val trailer: MutableList<Trailer> = mutableListOf()
        entity?.trailerList?.forEach {
            trailer.add(
                Trailer(
                    id = it.id,
                    key = it.key,
                    name = it.name,
                    site = it.site
                )
            )
        }

        val similar: MutableList<Similar> = mutableListOf()
        entity?.similarList?.forEach {
            similar.add(
                Similar(
                    id = it.id,
                    title = it.title,
                    posterPath = it.posterPath
                )
            )
        }

        return Movie(
            backdropPath = entity?.favorite?.backdropPath,
            genres = genre,
            id = entity?.favorite?.id ?: 0,
            overview = entity?.favorite?.overview,
            posterPath = entity?.favorite?.posterPath,
            releaseDate = entity?.favorite?.releaseDate,
            runtime = entity?.favorite?.runtime ?: 0,
            title = entity?.favorite?.title,
            credit = CreditResponse(credit),
            trailer = TrailerResponse(trailer),
            similar = SimilarResponse(similar),
            favorite = entity?.favorite?.favorite ?: 0
        )
    }
}
