package dev.playsit.core.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dev.playsit.core.network.ApiService
import dev.playsit.ui.modules.game.model.GameRepository
import dev.playsit.ui.modules.game.model.ReviewsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GameModule {

    @Provides
    @Singleton
    fun provideGameRepository(apiService: ApiService) = GameRepository(apiService)

    @Provides
    @Singleton
    fun provideReviewUseCase(gameRepository: GameRepository) = ReviewsUseCase(gameRepository)
}
