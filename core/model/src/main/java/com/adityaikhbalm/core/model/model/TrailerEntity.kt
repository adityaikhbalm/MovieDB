package com.adityaikhbalm.core.model.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "trailer",
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

data class TrailerEntity(
    @PrimaryKey
    var id: String = "0",
    var movieId: Int = 0,
    var key: String? = null,
    var name: String? = null,
    var site: String? = null
)
