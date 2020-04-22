package com.quipper.android.apollofrontpage.di

import com.quipper.android.apollofrontpage.PostListViewModel
import com.quipper.android.apollofrontpage.repository.PostsRepository
import com.quipper.android.apollofrontpage.repository.impl.PostsRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    factory<PostsRepository> { PostsRepositoryImpl() }
}

val viewModelModule = module {
    viewModel { PostListViewModel(postsRepository = get()) }
}