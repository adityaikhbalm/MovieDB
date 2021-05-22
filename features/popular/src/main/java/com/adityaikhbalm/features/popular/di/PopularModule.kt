package com.adityaikhbalm.features.popular.di

import com.adityaikhbalm.features.popular.viewmodel.PopularViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val popularModule = module {
    viewModel { PopularViewModel(get(), get()) }
}
