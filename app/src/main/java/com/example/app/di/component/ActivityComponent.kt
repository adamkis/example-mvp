package com.example.app.di.component

import com.example.app.di.module.ActivityModule
import com.example.app.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}