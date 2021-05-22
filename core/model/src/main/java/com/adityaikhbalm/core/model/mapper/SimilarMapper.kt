package com.adityaikhbalm.core.model.mapper

import com.adityaikhbalm.core.model.model.SimilarEntity
import com.adityaikhbalm.core.model.response.Movie

class SimilarMapper : EntityMapper<List<SimilarEntity>, Movie> {
    override fun mapToEntity(domain: Movie): List<SimilarEntity> {
        val similar = mutableListOf<SimilarEntity>()
        domain.similar?.results?.forEach {
            similar.add(
                SimilarEntity(
                    id = it.id,
                    movieId = domain.id,
                    title = it.title,
                    posterPath = it.posterPath
                )
            )
        }
        return similar
    }

    override fun mapFromEntity(entity: List<SimilarEntity>): Movie {
        throw UnsupportedOperationException("Not Supported")
    }
}
