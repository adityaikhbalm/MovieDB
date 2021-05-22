package com.adityaikhbalm.core.domain.di

import com.adityaikhbalm.core.domain.usecase.cache.*
import com.adityaikhbalm.core.domain.usecase.remote.*
import org.koin.dsl.module

val domainModule = module {
    factory { AllFavoriteUseCase(get()) }
    factory { DeleteUseCase(get()) }
    factory { FavoriteUseCase(get()) }
    factory { InsertUseCase(get()) }
    factory { SearchFavoriteUseCase(get()) }
    factory { BannerUseCase(get()) }
    factory { ComingUseCase(get()) }
    factory { DetailUseCase(get()) }
    factory { PopularPagingUseCase(get()) }
    factory { PopularUseCase(get()) }
    factory { SearchUseCase(get()) }
}
