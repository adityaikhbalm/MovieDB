package com.adityaikhbalm.core.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.adityaikhbalm.core.model.model.TrailerEntity

@Dao
interface TrailerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrailer(trailerList: List<TrailerEntity>)
}
