package dev.vladimir.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.home.data.repository.HomeRepository
import dev.vladimir.home.domain.repository.IHomeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindDataModule {

    @Singleton
    @Binds
    fun bindRepository(
        homeRepository: HomeRepository,
    ): IHomeRepository
}