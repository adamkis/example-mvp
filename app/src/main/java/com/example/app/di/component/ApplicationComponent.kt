package com.example.app.di.component

import com.example.app.di.module.*
import com.example.app.feature.weather.WeatherActivity
import com.example.app.feature.weather.WeatherPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    PresenterModule::class,
    RetrofitModule::class,
    ContextModule::class,
    GsonModule::class,
    WeatherApiModule::class
])
interface ApplicationComponent {
    fun inject(weatherPresenter: WeatherPresenter)
    fun inject(weatherActivity: WeatherActivity)
}