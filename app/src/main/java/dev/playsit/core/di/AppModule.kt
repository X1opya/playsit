package dev.playsit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.playsit.MainApplication

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideContext() = MainApplication().applicationContext
}
