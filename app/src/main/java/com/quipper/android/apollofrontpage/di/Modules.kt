package com.quipper.android.apollofrontpage.di

import com.quipper.android.apollofrontpage.PostListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    factory<com.quipper.android.apollofrontpage.repository.PostsRepository> { com.quipper.android.apollofrontpage.repository.impl.PostsRepository() }
}

val viewModelModule = module {
    viewModel { PostListViewModel(postsRepository = get()) }
}