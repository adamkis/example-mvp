package com.example.app.di.module

import com.example.app.api.WeatherApi
import com.example.app.feature.weather.mapper.WeatherDataMapper
import com.example.app.feature.weather.WeatherContract
import com.example.app.feature.weather.WeatherPresenter
import com.example.app.util.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun providePresenter(
            sharedPreferencesManager: SharedPreferencesManager,
            weatherApi: WeatherApi,
            weatherDataMapper: WeatherDataMapper
    ): WeatherContract.Presenter {
        return WeatherPresenter(sharedPreferencesManager, weatherApi, weatherDataMapper)
    }
}