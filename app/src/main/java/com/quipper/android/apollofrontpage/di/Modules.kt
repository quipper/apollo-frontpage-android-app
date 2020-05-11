package com.quipper.android.apollofrontpage.di

import com.apollographql.apollo.ApolloClient
import com.quipper.android.apollofrontpage.PostListViewModel
import com.quipper.android.apollofrontpage.repository.PostsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.quipper.android.apollofrontpage.repository.impl.PostsRepository as PostsRepositoryImpl

val apiModule = module {
    single { ApolloClient.builder().serverUrl("http://10.0.2.2:8080/graphql").build() }
}

val repositoryModule = module {
    factory<PostsRepository> { PostsRepositoryImpl(apolloClient = get()) }
}

val viewModelModule = module {
    viewModel { PostListViewModel(postsRepository = get()) }
}