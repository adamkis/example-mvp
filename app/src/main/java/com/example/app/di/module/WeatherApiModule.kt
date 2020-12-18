package com.example.app.di.module

import com.example.app.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class WeatherApiModule {
    @Provides
    @Singleton
    fun provideWeatherApiModule(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)
}
