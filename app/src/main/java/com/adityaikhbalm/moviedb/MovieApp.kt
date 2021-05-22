package com.adityaikhbalm.moviedb

import android.app.Application
import com.adityaikhbalm.core.cache.di.cacheModule
import com.adityaikhbalm.core.data.di.dataModule
import com.adityaikhbalm.core.domain.di.domainModule
import com.adityaikhbalm.core.remote.di.remoteModule
import com.adityaikhbalm.features.detail.di.detailModule
import com.adityaikhbalm.features.favorite.di.favoriteModule
import com.adityaikhbalm.features.home.di.homeModule
import com.adityaikhbalm.features.popular.di.popularModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApp : Application() {
    private val moduleList by lazy {
        listOf(
            domainModule,
            dataModule,
            cacheModule,
            remoteModule,
            homeModule,
            popularModule,
            favoriteModule,
            detailModule
        )
    }

    override fun onCreate() {
        super.onCreate()
        injectKoin()
    }

    private fun injectKoin() {
        startKoin {
            androidContext(this@MovieApp)
            androidLogger()
            modules(moduleList)
        }
    }
}
