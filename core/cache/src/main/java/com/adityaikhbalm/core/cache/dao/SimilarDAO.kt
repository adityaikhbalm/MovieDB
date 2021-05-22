package com.adityaikhbalm.core.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.adityaikhbalm.core.model.model.SimilarEntity

@Dao
interface SimilarDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSimilar(similarList: List<SimilarEntity>)
}
