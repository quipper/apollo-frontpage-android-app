package com.quipper.android.apollofrontpage.app

import android.app.Application
import com.quipper.android.apollofrontpage.di.apiModule
import com.quipper.android.apollofrontpage.di.repositoryModule
import com.quipper.android.apollofrontpage.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApolloFrontPageApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@ApolloFrontPageApp)
            modules(apiModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
    }
}