package com.example.app.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class GsonModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}
