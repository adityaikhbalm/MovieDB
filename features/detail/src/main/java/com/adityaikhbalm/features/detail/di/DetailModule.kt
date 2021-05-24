package com.adityaikhbalm.features.detail.di

import com.adityaikhbalm.features.detail.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel { DetailViewModel(get(), get(), get()) }
}
