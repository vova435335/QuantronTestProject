package dev.vladimir.details.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.details.data.repository.MediaDetailsRepository
import dev.vladimir.details.domain.repository.IMediaDetailRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindDataModule {

    @Singleton
    @Binds
    fun bindRepository(
        mediaDetailsRepository: MediaDetailsRepository,
    ): IMediaDetailRepository
}