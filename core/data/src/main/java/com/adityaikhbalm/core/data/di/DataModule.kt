package com.adityaikhbalm.core.data.di

import com.adityaikhbalm.core.data.MovieDataRepository
import com.adityaikhbalm.core.data.store.MovieCacheDataStore
import com.adityaikhbalm.core.data.store.MovieRemoteDataStore
import com.adityaikhbalm.core.domain.repository.MovieRepository
import com.adityaikhbalm.core.model.mapper.MovieResponseMapper
import org.koin.dsl.module

val dataModule = module {
    single { MovieResponseMapper() }
    single { MovieRemoteDataStore(get()) }
    single { MovieCacheDataStore(get()) }
    single<MovieRepository> { MovieDataRepository(get(), get(), get(), get()) }
}
