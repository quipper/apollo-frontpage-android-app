package com.quipper.android.apollofrontpage.app

import android.app.Application
import com.quipper.android.apollofrontpage.PostListViewModel
import com.quipper.android.apollofrontpage.repository.impl.PostsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ApolloFrontPageApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@ApolloFrontPageApp)
            modules(viewModelModule)
            modules(repositoryModule)
        }
    }
}

val viewModelModule = module {
    viewModel { PostListViewModel(get()) }
}

val repositoryModule = module {
    single { PostsRepositoryImpl() }
}