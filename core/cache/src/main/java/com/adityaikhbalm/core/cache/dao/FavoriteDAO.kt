package com.adityaikhbalm.core.cache.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.adityaikhbalm.core.model.model.MovieDetailEntity
import com.adityaikhbalm.core.model.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorite")
    fun getAllFavorite(): PagingSource<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getFavorite(id: Int): Flow<MovieDetailEntity>

    @Query("SELECT * FROM favorite WHERE title LIKE '%'||:keyword||'%'")
    fun searchFavorite(keyword: String): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: MovieEntity?)

    @Delete
    suspend fun deleteFavorite(movie: MovieEntity?)
}
