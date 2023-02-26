package dev.vladimir.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.auth.data.repository.AuthRepository
import dev.vladimir.auth.domain.repository.IAuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindDataModule {

    @Singleton
    @Binds
    fun bindRepository(
        authRepository: AuthRepository,
    ): IAuthRepository
}
