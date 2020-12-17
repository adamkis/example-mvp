package com.example.app.di.module

import android.content.Context
import com.example.app.core.AppContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppContextModule(private val applicationContext: Context) {
    @Provides
    @Singleton
    fun provideAppContext(): AppContext = AppContext(applicationContext)
}