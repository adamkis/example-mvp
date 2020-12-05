package com.example.app.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val appContext: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = appContext
}