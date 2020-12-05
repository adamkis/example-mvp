package com.example.app.di.module

import com.example.app.ui.main.MainContract
import com.example.app.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun providePresenter(retrofit: Retrofit): MainContract.Presenter {
        return MainPresenter(retrofit)
    }

}