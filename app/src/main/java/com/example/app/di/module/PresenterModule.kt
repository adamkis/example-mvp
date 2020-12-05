package com.example.app.di.module

import com.example.app.ui.main.MainContract
import com.example.app.ui.main.MainPresenter
import com.example.app.util.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun providePresenter(retrofit: Retrofit, sharedPreferencesManager: SharedPreferencesManager): MainContract.Presenter {
        return MainPresenter(sharedPreferencesManager, retrofit)
    }

}