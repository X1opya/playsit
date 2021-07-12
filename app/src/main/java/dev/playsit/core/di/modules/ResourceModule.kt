package dev.playsit.core.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dev.playsit.core.utils.ResourceProvider
import dev.playsit.core.utils.ResourceProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ResourceModule {

    @Provides
    @Singleton
    fun provideResource(context: Context) = ResourceProviderImpl(context)
}
@Module
@InstallIn(SingletonComponent::class)
interface ResourceBinder {
    @Binds
    fun bindResourceProvider(provider: ResourceProviderImpl): ResourceProvider
}
