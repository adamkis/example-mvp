package com.example.app.di.component

import com.example.app.BaseApp
import com.example.app.di.module.ApplicationModule
import com.example.app.di.module.RetrofitModule
import com.example.app.ui.main.MainActivity
import com.example.app.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RetrofitModule::class])
interface ApplicationComponent {
    fun inject(application: BaseApp)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)

}