package com.example.app.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GsonModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}