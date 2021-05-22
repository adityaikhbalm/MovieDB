package com.adityaikhbalm.core.model.mapper

import com.adityaikhbalm.core.model.model.CastEntity
import com.adityaikhbalm.core.model.response.Movie

class CastMapper : EntityMapper<List<CastEntity>, Movie> {
    override fun mapToEntity(domain: Movie): List<CastEntity> {
        val cast = mutableListOf<CastEntity>()
        domain.credit?.cast?.forEach {
            cast.add(
                CastEntity(
                    id = it.id,
                    movieId = domain.id,
                    name = it.name,
                    profilePath = it.profilePath,
                    order = it.order
                )
            )
        }
        return cast
    }

    override fun mapFromEntity(entity: List<CastEntity>): Movie {
        throw UnsupportedOperationException("Not Supported")
    }
}
