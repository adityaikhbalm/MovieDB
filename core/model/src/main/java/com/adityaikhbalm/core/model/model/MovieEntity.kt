package com.adityaikhbalm.core.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class MovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var backdropPath: String? = null,
    var genres: List<GenreEntity>? = null,
    var overview: String? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var runtime: Int = 0,
    var title: String? = null,
    var favorite: Int = 0
)
