package com.adityaikhbalm.core.model.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cast",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ],
    indices = [Index(value = ["movieId"])]
)

data class CastEntity(
    @PrimaryKey
    var id: Int = 0,
    var movieId: Int = 0,
    var name: String? = null,
    var profilePath: String? = null,
    var order: Int = 0
)
