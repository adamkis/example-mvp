package com.example.app

import android.app.Application
import com.example.app.di.component.ApplicationComponent
import com.example.app.di.component.DaggerApplicationComponent
import com.example.app.di.module.ContextModule

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val component: ApplicationComponent  = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .build()

    companion object {
        lateinit var instance: BaseApp private set
    }
}