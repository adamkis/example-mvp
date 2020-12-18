package com.example.app.di.module

import com.example.app.core.AppContext
import com.example.app.core.ResourceResolver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ResourceResolverModule {
    @Provides
    @Singleton
    fun provideResourceResolver(appContext: AppContext): ResourceResolver = ResourceResolver(appContext)
}
