package com.example.app.di.component

import com.example.app.di.module.ContextModule
import com.example.app.di.module.PresenterModule
import com.example.app.di.module.RetrofitModule
import com.example.app.ui.main.MainActivity
import com.example.app.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class, RetrofitModule::class, ContextModule::class])
interface ApplicationComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
}