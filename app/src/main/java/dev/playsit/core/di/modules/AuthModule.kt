package dev.playsit.core.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.playsit.auth.AuthManager
import dev.playsit.auth.LogoutManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideToken(context: Context) = AuthManager(context)

    @Provides
    @Singleton
    fun provideToken(authManager: AuthManager) = LogoutManager(authManager)
}
