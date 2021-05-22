package com.adityaikhbalm.core.model.model

import androidx.room.Embedded
import androidx.room.Relation

data class MovieDetailEntity(
    @Embedded
    var favorite: MovieEntity? = null,

    @Relation(parentColumn = "id", entityColumn = "movieId")
    var castList: List<CastEntity>? = null,

    @Relation(parentColumn = "id", entityColumn = "movieId")
    var trailerList: List<TrailerEntity>? = null,

    @Relation(parentColumn = "id", entityColumn = "movieId")
    var similarList: List<SimilarEntity>? = null
)
