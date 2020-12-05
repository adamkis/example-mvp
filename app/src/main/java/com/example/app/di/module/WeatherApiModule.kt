package com.example.app.di.module

import com.example.app.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class WeatherApiModule {
    @Provides
    @Singleton
    fun provideWeatherApiModule(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)
}