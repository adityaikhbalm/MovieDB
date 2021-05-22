package com.adityaikhbalm.core.model.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "similar",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["movieId"])]
)

data class SimilarEntity(
    @PrimaryKey
    var id: Int = 0,
    var movieId: Int = 0,
    var title: String? = null,
    var posterPath: String? = null
)
