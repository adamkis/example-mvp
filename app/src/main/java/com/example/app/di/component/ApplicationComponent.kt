package com.example.app.di.component

import com.example.app.di.module.*
import com.example.app.ui.main.MainActivity
import com.example.app.ui.main.MainPresenter
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
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
}