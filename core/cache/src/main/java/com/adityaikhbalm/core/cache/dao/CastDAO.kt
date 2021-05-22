package com.adityaikhbalm.core.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.adityaikhbalm.core.model.model.CastEntity

@Dao
interface CastDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCast(castList: List<CastEntity>)
}
