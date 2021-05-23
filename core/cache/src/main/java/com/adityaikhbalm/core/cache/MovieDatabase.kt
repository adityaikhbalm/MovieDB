package com.adityaikhbalm.core.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adityaikhbalm.core.cache.dao.CastDAO
import com.adityaikhbalm.core.cache.dao.FavoriteDAO
import com.adityaikhbalm.core.cache.dao.SimilarDAO
import com.adityaikhbalm.core.cache.dao.TrailerDAO
import com.adityaikhbalm.core.model.model.*

@Database(
    entities = [MovieEntity::class, TrailerEntity::class, CastEntity::class, SimilarEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun favoriteDAO(): FavoriteDAO
    abstract fun trailerDAO(): TrailerDAO
    abstract fun castDAO(): CastDAO
    abstract fun similarDAO(): SimilarDAO

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        operator fun invoke(context: Context) =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java, "movie"
            )
                .build()
    }
}
