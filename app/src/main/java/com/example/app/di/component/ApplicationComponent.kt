package com.example.app.di.component

import com.example.app.di.module.*
import com.example.app.feature.weather.WeatherActivity
import com.example.app.feature.weather.WeatherPresenter
import com.example.app.feature.weatherList.WeatherListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class,
    AppContextModule::class,
    GsonModule::class,
    WeatherApiModule::class,
    ResourceResolverModule::class
])
interface ApplicationComponent {
    fun inject(weatherPresenter: WeatherPresenter)
    fun inject(weatherActivity: WeatherActivity)
    fun inject(weatherListActivity: WeatherListActivity)
}