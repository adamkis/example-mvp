package com.example.app.di.module

import android.app.Application
import com.example.app.BaseApp
import com.example.app.ui.main.MainContract
import com.example.app.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }

    @Provides
    @Singleton
    fun providePresenter(retrofit: Retrofit): MainContract.Presenter {
        return MainPresenter(retrofit)
    }

}