package dev.vladimir.profile.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.profile.presentation.data.repository.ProfileRepository
import dev.vladimir.profile.presentation.domain.repository.IProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindDataModule {

    @Singleton
    @Binds
    fun bindRepository(
        profileRepository: ProfileRepository,
    ): IProfileRepository
}