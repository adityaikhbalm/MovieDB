package com.adityaikhbalm.core.cache.di

import com.adityaikhbalm.core.cache.MovieCacheImpl
import com.adityaikhbalm.core.cache.MovieDatabase
import com.adityaikhbalm.core.data.repository.MovieCache
import com.adityaikhbalm.core.model.mapper.*
import org.koin.dsl.module

val cacheModule = module {
    single<MovieCache> {
        MovieCacheImpl(get(), get(), get(), get(), get())
    }
    factory { FavoriteMapper() }
    factory { CastMapper() }
    factory { TrailerMapper() }
    factory { SimilarMapper() }
    factory { FavoriteDetailMapper() }
    single { MovieDatabase(get()) }
}
