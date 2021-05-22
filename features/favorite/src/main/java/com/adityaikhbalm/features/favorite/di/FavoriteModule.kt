package com.adityaikhbalm.features.favorite.di

import com.adityaikhbalm.features.favorite.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get(), get(), get(), get()) }
}
