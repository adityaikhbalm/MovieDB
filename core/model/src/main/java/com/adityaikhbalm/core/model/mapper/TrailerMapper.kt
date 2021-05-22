package com.adityaikhbalm.core.model.mapper

import com.adityaikhbalm.core.model.model.TrailerEntity
import com.adityaikhbalm.core.model.response.Movie

class TrailerMapper : EntityMapper<List<TrailerEntity>, Movie> {
    override fun mapToEntity(domain: Movie): List<TrailerEntity> {
        val trailer = mutableListOf<TrailerEntity>()
        domain.trailer?.results?.forEach {
            trailer.add(
                TrailerEntity(
                    id = it.id,
                    movieId = domain.id,
                    key = it.key,
                    name = it.name,
                    site = it.site
                )
            )
        }
        return trailer
    }

    override fun mapFromEntity(entity: List<TrailerEntity>): Movie {
        throw UnsupportedOperationException("Not Supported")
    }
}
