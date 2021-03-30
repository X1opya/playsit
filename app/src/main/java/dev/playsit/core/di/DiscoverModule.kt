package dev.playsit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.playsit.core.network.ApiService
import dev.playsit.repository.FeedRepository

@Module
@InstallIn(ViewModelComponent::class)
internal object DiscoverModule {
    @ViewModelScoped
    @Provides
    fun provideFeedRepository(api: ApiService) = FeedRepository(api)
}