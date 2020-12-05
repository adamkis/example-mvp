package com.example.app.di.module

import android.content.Context
import com.example.app.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ContextModule(private val appContext: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = appContext
}