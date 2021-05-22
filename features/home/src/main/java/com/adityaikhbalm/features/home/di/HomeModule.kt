package com.adityaikhbalm.features.home.di

import com.adityaikhbalm.features.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
}
