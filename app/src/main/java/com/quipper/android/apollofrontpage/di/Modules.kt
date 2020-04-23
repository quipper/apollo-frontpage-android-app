package com.quipper.android.apollofrontpage.di

import com.quipper.android.apollofrontpage.PostListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PostListViewModel() }
}