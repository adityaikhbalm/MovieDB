package com.adityaikhbalm.core.model.model

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object Converter {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val type = Types.newParameterizedType(List::class.java, GenreEntity::class.java)
    private val adapter = moshi.adapter<List<GenreEntity>>(type)

    @TypeConverter
    @JvmStatic
    fun fromGenreList(genres: List<GenreEntity>?): String? {
        return adapter.toJson(genres)
    }

    @TypeConverter
    @JvmStatic
    fun toGenreList(genres: String): List<GenreEntity>? {
        return adapter.fromJson(genres)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringList(list: List<String>?): String? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun toStringList(list: String): List<String>? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.fromJson(list)
    }
}
