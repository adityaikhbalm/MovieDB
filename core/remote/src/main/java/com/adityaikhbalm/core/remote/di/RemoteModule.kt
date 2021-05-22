package com.adityaikhbalm.core.remote.di

import com.adityaikhbalm.core.data.repository.MovieRemote
import com.adityaikhbalm.core.remote.MovieRemoteImpl
import com.adityaikhbalm.core.remote.service.MovieApiService
import org.koin.dsl.module

val remoteModule = module {
    single { MovieApiService() }
    single<MovieRemote> { MovieRemoteImpl(get()) }
}
