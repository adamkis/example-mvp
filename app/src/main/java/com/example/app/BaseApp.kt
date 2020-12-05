package com.example.app

import android.app.Application
import com.example.app.di.component.ApplicationComponent
import com.example.app.di.component.DaggerApplicationComponent

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val component: ApplicationComponent  = DaggerApplicationComponent.builder().build()

    companion object {
        lateinit var instance: BaseApp private set
    }
}